package com.profiletweets.controller;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping({"/portfolio"})
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @PostMapping
    public Portfolio create(@RequestBody Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }

    @GetMapping
    public List<Portfolio> findAll(){
        return portfolioRepository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity findPortfolio(@PathVariable long id){
        return portfolioRepository.findById(id)
                .map(portfolio -> ResponseEntity.ok().body(portfolio)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Portfolio portfolio) {

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

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {

        return portfolioRepository.findById(id)
                .map(record -> {
                    portfolioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
