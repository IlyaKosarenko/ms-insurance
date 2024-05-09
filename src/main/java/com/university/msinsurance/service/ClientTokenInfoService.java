package com.university.msinsurance.service;

import com.university.msinsurance.exception.EmailNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientTokenInfoService {

    public String getEmailFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(auth.getName()).orElseThrow(() -> new EmailNotFoundException("Email not found from authentication token"));
    }

}
