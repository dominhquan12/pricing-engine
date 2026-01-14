package com.develop.pricingengine.repo;

import com.develop.pricingengine.domain.TaxTranche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaxTrancheRepository extends JpaRepository<TaxTranche, Long> {

    @Query("""
                select p from TaxTranche p
                where :date >= p.validFrom
                  and (p.validTo is null or :date <= p.validTo)
            """)
    List<TaxTranche> findByDate(LocalDate date);

    @Query("""
                select p from TaxTranche p
                where p.code = :inputCode
                    and :date >= p.validFrom
                  and (p.validTo is null or :date <= p.validTo)
            """)
    Optional<TaxTranche> findByCodeAndDate(String inputCode, LocalDate date);
}


