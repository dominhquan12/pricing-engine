package com.develop.pricingengine.service;

import com.develop.pricingengine.domain.TaxTranche;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TrancheCalculator {

    public Map<String, BigDecimal> calculateTranches(
            BigDecimal usage,
            List<TaxTranche> tranches
    ) {
        Map<String, BigDecimal> result = new HashMap<>();

        BigDecimal remaining = usage.max(BigDecimal.ZERO);

        for (TaxTranche t : tranches) {
            if (remaining.signum() <= 0) {
                result.put(t.getCode() + "_USAGE", BigDecimal.ZERO);
                continue;
            }

            BigDecimal trancheSize = t.getToBound().subtract(t.getFromBound()).add(BigDecimal.ONE);
            BigDecimal trancheUsage = remaining.min(trancheSize);

            result.put(t.getCode() + "_USAGE", trancheUsage);
            remaining = remaining.subtract(trancheUsage);
        }

        return result;
    }
}
