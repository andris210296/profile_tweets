package com.profiletweets.service;

import com.profiletweets.model.portfolio.Portfolio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PortfolioService {

    ResponseEntity createPortfolio(@RequestBody Portfolio portfolio);
    ResponseEntity findAllPortfolios();
    ResponseEntity findPortfolio(@PathVariable long id);
    ResponseEntity updatePortfolio(@PathVariable("id") long id, @RequestBody Portfolio portfolio);
    ResponseEntity deletePortfolio(@PathVariable long id);

}
