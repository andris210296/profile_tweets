package com.profiletweets.service.impl;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.repository.PortfolioRepository;
import com.profiletweets.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public ResponseEntity createPortfolio(Portfolio portfolio) {
        return ResponseEntity.ok().body(portfolioRepository.save(portfolio));
    }

    @Override
    public ResponseEntity findAllPortfolios() {
        return ResponseEntity.ok().body(portfolioRepository.findAll());
    }

    @Override
    public ResponseEntity findPortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(portfolio -> ResponseEntity.ok().body(portfolio)).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity updatePortfolio(long id, Portfolio portfolio) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    record.setImagePath(portfolio.getImagePath());
                    record.setName(portfolio.getName());
                    record.setExperience(portfolio.getExperience());
                    record.setTwitterUserName(portfolio.getTwitterUserName());
                    Portfolio updated = portfolioRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deletePortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    portfolioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
