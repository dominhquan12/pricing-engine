package com.develop.pricingengine.repo;

import com.develop.pricingengine.domain.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FormulaRepository extends JpaRepository<Formula, Long> {

    @Query("""
        select f from Formula f
        where :date >= f.validFrom
        and (f.validTo is null or :date <= f.validTo)
    """)
    List<Formula> findByDate(LocalDate date);
}

