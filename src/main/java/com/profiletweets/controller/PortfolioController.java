package com.profiletweets.controller;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/portfolio"})
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity create(@RequestBody Portfolio portfolio){

        return portfolioService.createPortfolio(portfolio)
                .map(portfolioOutput -> ResponseEntity.ok()
                        .body(portfolioOutput))
                .orElse(ResponseEntity.internalServerError().build());
    }

    @PostMapping(path = { "/{id}" })
    public ResponseEntity addImage(@PathVariable long id, @RequestParam("imageFile")MultipartFile imageFile){

        return portfolioService.addImageInPortfolio(id, imageFile)
                .map(portfolioOutput -> ResponseEntity.ok()
                        .body(portfolioOutput))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok().body(portfolioService.findAllPortfolios());
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity findPortfolio(@PathVariable long id){
        return portfolioService.findPortfolio(id)
                .map(portfolioOutput ->
                        ResponseEntity.ok().body(portfolioOutput))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Portfolio portfolio) {
        return portfolioService.updatePortfolio(id, portfolio)
                .map(portfolioOutput ->
                        ResponseEntity.ok().body(portfolioOutput))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity delete(@PathVariable long id) {
        return portfolioService.deletePortfolio(id)
                .map(portfolioOutput ->
                        ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }

}
