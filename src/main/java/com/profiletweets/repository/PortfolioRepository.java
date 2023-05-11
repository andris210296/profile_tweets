package com.profiletweets.repository;

import com.profiletweets.model.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    @Override
    Optional<Portfolio> findById(Long integer);
}
