package com.develop.pricingengine.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class TaxTranche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    BigDecimal fromBound;
    BigDecimal toBound;
    String code;

    @Column(precision = 38, scale = 5)
    BigDecimal value;

    private LocalDate validFrom;
    private LocalDate validTo;
}
