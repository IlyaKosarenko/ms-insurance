package com.university.msinsurance.service;

import com.university.msinsurance.repository.InsuranceRepository;
import com.university.msinsurance.entity.Insurance;
import com.university.msinsurance.response.InsuranceInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final ClientTokenInfoService clientTokenInfoService;


    public InsuranceService(InsuranceRepository insuranceRepository,
                            ClientTokenInfoService clientTokenInfoService) {
        this.insuranceRepository = insuranceRepository;
        this.clientTokenInfoService = clientTokenInfoService;
    }


    public List<InsuranceInfoResponse> getInsurances() {
        String email = clientTokenInfoService.getEmailFromToken();
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

}
