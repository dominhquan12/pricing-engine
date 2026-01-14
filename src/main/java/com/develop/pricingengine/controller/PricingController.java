package com.develop.pricingengine.controller;

import com.develop.pricingengine.dto.PricingRequest;
import com.develop.pricingengine.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateDb(@RequestBody PricingRequest request) {
        Object result = pricingService.calculate(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


