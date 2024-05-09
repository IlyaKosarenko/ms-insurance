package com.university.msinsurance.endpoint;

import com.university.msinsurance.response.InsuranceInfoResponse;
import com.university.msinsurance.service.InsuranceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsuranceEndpoint {

    private final InsuranceService insuranceService;

    public InsuranceEndpoint(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }


    @GetMapping("/insurance")
    public List<InsuranceInfoResponse> getInsurances() {
        return insuranceService.getInsurances();
    }

}
