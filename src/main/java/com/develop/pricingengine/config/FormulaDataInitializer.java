package com.develop.pricingengine.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@RequiredArgsConstructor
public class FormulaDataInitializer {

    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        populator.addScript(new ClassPathResource("sql/price_202601141356.sql"));
        populator.addScript(new ClassPathResource("sql/tax_tranche_202601141356.sql"));
        populator.addScript(new ClassPathResource("sql/formula_202601141355.sql"));
        populator.addScript(new ClassPathResource("sql/formula_input_202601141356.sql"));

        populator.setContinueOnError(false);
        populator.setSeparator(";");
        log.info("Database populator: {}", populator);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}

