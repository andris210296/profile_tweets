package com.profiletweets.service;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {

    Optional<PortfolioOutput> createPortfolio(Portfolio portfolio);
    Optional<PortfolioOutput> addImageInPortfolio(long id, MultipartFile imageFile);
    Optional<List<PortfolioOutput>> findAllPortfolios();
    Optional<PortfolioOutput> findPortfolio(long id);
    Optional<PortfolioOutput> updatePortfolio(long id, Portfolio portfolio);
    Optional<PortfolioOutput> deletePortfolio(long id);

}
