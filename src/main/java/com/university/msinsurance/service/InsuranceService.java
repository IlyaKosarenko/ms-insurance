package com.university.msinsurance.service;

import com.university.msinsurance.entity.Department;
import com.university.msinsurance.entity.Type;
import com.university.msinsurance.exception.DepartmentNotFoundException;
import com.university.msinsurance.exception.TypeNotFoundException;
import com.university.msinsurance.repository.DepartmentRepository;
import com.university.msinsurance.repository.InsuranceRepository;
import com.university.msinsurance.entity.Insurance;
import com.university.msinsurance.repository.TypeRepository;
import com.university.msinsurance.request.InsuranceCreateRequest;
import com.university.msinsurance.response.InsuranceInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final DepartmentRepository departmentRepository;

    private final TypeRepository typeRepository;

    private final ClientTokenInfoService clientTokenInfoService;


    public InsuranceService(InsuranceRepository insuranceRepository,
                            DepartmentRepository departmentRepository,
                            TypeRepository typeRepository,
                            ClientTokenInfoService clientTokenInfoService) {
        this.insuranceRepository = insuranceRepository;
        this.departmentRepository = departmentRepository;
        this.typeRepository = typeRepository;
        this.clientTokenInfoService = clientTokenInfoService;
    }


    public List<InsuranceInfoResponse> getInsurances() {
        String email = clientTokenInfoService.getEmailFromToken();
        return insuranceRepository.getInsurancesByEmail(email).stream().map(this::map).toList();
    }

    public String createInsurance(InsuranceCreateRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        Type type = typeRepository.findByName(request.getType())
                .orElseThrow(() -> new TypeNotFoundException("Type not found"));

        String contractNumber = UUID.randomUUID().toString();

        return insuranceRepository.save(toEntity(request, department, type, contractNumber)).getContractNumber();
    }

    public List<InsuranceInfoResponse> getInsurancesByEmail(String email) {
        return insuranceRepository.getInsurancesByEmail(email).stream().map(this::map).toList();
    }

    private InsuranceInfoResponse map(Insurance insurance) {
        InsuranceInfoResponse response = new InsuranceInfoResponse();
        response.setContractNumber(insurance.getContractNumber());
        response.setDepartment(insurance.getDepartment().getName());
        response.setPrice(insurance.getPrice());
        response.setType(insurance.getType().getName());
        response.setEndDate(insurance.getEndDate());
        response.setStartDate(insurance.getStartDate());
        return response;
    }

    private Insurance toEntity(InsuranceCreateRequest request, Department department, Type type, String contractNumber) {
        Insurance insurance = new Insurance();
        insurance.setDepartment(department);
        insurance.setType(type);
        insurance.setPrice(request.getPrice());
        insurance.setEmail(clientTokenInfoService.getEmailFromToken());
        insurance.setStartDate(request.getStartDate());
        insurance.setEndDate(request.getEndDate());
        insurance.setContractNumber(contractNumber);
        return insurance;
    }

}
