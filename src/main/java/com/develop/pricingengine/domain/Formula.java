package com.develop.pricingengine.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String expression;

    @OneToMany(mappedBy = "formula")
    List<FormulaInput> inputs;
}

