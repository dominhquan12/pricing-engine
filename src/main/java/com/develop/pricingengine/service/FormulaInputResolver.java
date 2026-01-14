package com.develop.pricingengine.service;

import com.develop.pricingengine.domain.FormulaInput;
import com.develop.pricingengine.repo.PriceRepository;
import com.develop.pricingengine.repo.TaxTrancheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class FormulaInputResolver {

    private final PriceRepository priceRepo;
    private final TaxTrancheRepository trancheRepo;

    public Object resolve(FormulaInput input, LocalDate date, PricingContext context) {
        return switch (input.getType()) {

            case PRICE -> priceRepo.findByDate(input.getInputCode(), date)
                    .orElseThrow(() -> new IllegalStateException(
                            "Missing PRICE: " + input.getInputCode()
                    ))
                    .getValue();

            case TRANCHE_PRICE -> trancheRepo.findByCodeAndDate(input.getInputCode(), date)
                    .orElseThrow(() -> new IllegalStateException(
                            "Missing TRANCHE PRICE: " + input.getInputCode()
                    ))
                    .getValue();
        };
    }
}


