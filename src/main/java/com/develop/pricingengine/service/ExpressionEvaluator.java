package com.develop.pricingengine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class ExpressionEvaluator {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public BigDecimal evaluate(String expression, Map<String, Object> vars) {
        var ctx = new StandardEvaluationContext();
        vars.forEach(ctx::setVariable);
        System.out.println(objectMapper.writeValueAsString(vars));
        System.out.println(objectMapper.writeValueAsString(expression));
        return parser.parseExpression(expression).getValue(ctx, BigDecimal.class);
    }
}

