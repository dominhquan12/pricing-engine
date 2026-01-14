package com.develop.pricingengine.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PricingRequest {
    private BigDecimal usage;
    private BigDecimal feedIn;
    private LocalDate pricingDate;
}

