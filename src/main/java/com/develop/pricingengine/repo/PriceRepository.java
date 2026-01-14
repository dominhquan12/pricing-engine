package com.develop.pricingengine.repo;

import com.develop.pricingengine.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("""
                select p from Price p
                where p.code = :type
                  and :date >= p.validFrom
                  and (p.validTo is null or :date <= p.validTo)
            """)
    Optional<Price> findByDate(String type, LocalDate date);
}


