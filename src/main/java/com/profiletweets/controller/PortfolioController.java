package com.profiletweets.controller;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping({"/portfolio"})
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity create(@RequestBody Portfolio portfolio){
        return portfolioService.createPortfolio(portfolio);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return portfolioService.findAllPortfolios();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity findPortfolio(@PathVariable long id){
        return portfolioService.findPortfolio(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Portfolio portfolio) {
        return portfolioService.updatePortfolio(id, portfolio);
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity delete(@PathVariable long id) {
        return portfolioService.deletePortfolio(id);
    }

}
