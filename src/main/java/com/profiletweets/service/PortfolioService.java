package com.profiletweets.service;

import com.profiletweets.model.portfolio.Portfolio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface PortfolioService {

    ResponseEntity createPortfolio(Portfolio portfolio);
    ResponseEntity addImageInPortfolio(long id, MultipartFile imageFile);
    ResponseEntity findAllPortfolios();
    ResponseEntity findPortfolio(long id);
    ResponseEntity updatePortfolio(long id, Portfolio portfolio);
    ResponseEntity deletePortfolio(long id);

}
