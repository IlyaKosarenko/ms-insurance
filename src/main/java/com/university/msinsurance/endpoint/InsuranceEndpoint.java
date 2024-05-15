package com.university.msinsurance.endpoint;

import com.university.msinsurance.request.InsuranceCreateRequest;
import com.university.msinsurance.response.InsuranceInfoResponse;
import com.university.msinsurance.service.InsuranceService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class InsuranceEndpoint {

    private final InsuranceService insuranceService;

    public InsuranceEndpoint(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }


    @GetMapping("/insurance")
    @PreAuthorize("hasAuthority('CLIENT')")
    public List<InsuranceInfoResponse> getInsurances() {
        return insuranceService.getInsurances();
    }

    @PostMapping("/insurance")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<Map<String, String>> createInsurance(@RequestBody InsuranceCreateRequest request) {
        return new ResponseEntity<>(Map.of("contractNumber", insuranceService.createInsurance(request)), HttpStatusCode.valueOf(200));

    }

    @GetMapping("/internal/insurance")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InsuranceInfoResponse> getInsurancesByEmail(@RequestParam String email) {
        return insuranceService.getInsurancesByEmail(email);
    }

}
