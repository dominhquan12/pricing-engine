package com.develop.pricingengine.domain;

import com.develop.pricingengine.domain.enumeration.InputType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FormulaInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Formula formula;

    private String inputCode;

    @Enumerated(EnumType.STRING)
    private InputType type;
}
