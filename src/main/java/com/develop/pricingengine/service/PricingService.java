package com.develop.pricingengine.service;

import com.develop.pricingengine.domain.Formula;
import com.develop.pricingengine.dto.PricingRequest;
import com.develop.pricingengine.repo.FormulaRepository;
import com.develop.pricingengine.repo.TaxTrancheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final FormulaRepository formulaRepo;
    private final PricingEngine engine;
    private final TrancheCalculator trancheCalculator;
    private final TaxTrancheRepository trancheRepo;

    public Map<String, BigDecimal> calculate(PricingRequest request) {
        List<Formula> formulas = formulaRepo.findByDate(request.getPricingDate());
        Map<String, BigDecimal> formulaMap = new HashMap<>();

        for (Formula formula : formulas) {
            PricingContext ctx = new PricingContext();
            ctx.put("usage", request.getUsage());
            ctx.put("feedIn", request.getFeedIn());

            BigDecimal netUsage = request.getUsage()
                    .subtract(request.getFeedIn())
                    .max(BigDecimal.ZERO);

            Map<String, BigDecimal> trancheUsages = trancheCalculator.calculateTranches(
                    netUsage,
                    trancheRepo.findByDate(request.getPricingDate())
            );

            trancheUsages.forEach(ctx::put);

            formulaMap.put(formula.getCode(), engine.calculate(request.getPricingDate(), formula, ctx));
        }
        return formulaMap;
    }

}

