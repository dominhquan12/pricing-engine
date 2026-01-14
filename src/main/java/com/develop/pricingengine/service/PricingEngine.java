package com.develop.pricingengine.service;

import com.develop.pricingengine.domain.Formula;
import com.develop.pricingengine.domain.FormulaInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PricingEngine {

    private final ExpressionEvaluator evaluator;
    private final FormulaInputResolver resolver;

    public BigDecimal calculate(LocalDate pricingDate, Formula formula, PricingContext ctx) {
        for (FormulaInput input : formula.getInputs()) {
            Object value = resolver.resolve(input, pricingDate, ctx);
            if (!ctx.contains(input.getInputCode())) {
                ctx.put(input.getInputCode(), value);
            }
        }

        BigDecimal result = evaluator.evaluate(formula.getExpression(), ctx.asMap());
        return result;
    }
}
