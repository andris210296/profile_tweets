package com.profiletweets.controller;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("portfolioOutputs", portfolioService.findAllPortfolios().get());
        return "index";
    }

    @GetMapping("/newPortfolioForm")
    public String showNewPortfolioForm(Model model){
        Portfolio portfolio = new Portfolio();
        model.addAttribute("portfolio", portfolio);
        return "newPortfolioForm";
    }

    @PostMapping("/savePortfolio")
    public String savePortfolio(@ModelAttribute("portfolio") Portfolio portfolio){

        if(portfolio.getIdPortfolio() == null){
            portfolioService.createPortfolio(portfolio);
        }else{
            portfolioService.updatePortfolio(portfolio.getIdPortfolio(), portfolio );
        }

        return "redirect:/";
    }

    @PostMapping("/saveImage")
    public String saveImage(@ModelAttribute("portfolio") Portfolio portfolio, @RequestParam("imageFile") MultipartFile imageFile){

        portfolioService.addImageInPortfolio(portfolio.getIdPortfolio(), imageFile);
        return "redirect:/";
    }


    @GetMapping("/openUpdatePortfolioForm/{id}")
    public String updatePortfolioForm(@PathVariable long id, Model model){
        PortfolioOutput portfolioOutput = portfolioService.findPortfolio(id).get();
        model.addAttribute("portfolioOutput", portfolioOutput);
        return "updatePortfolioForm";
    }

    @GetMapping("/deletePortfolio/{id}")
    public String deletePortfolio(@PathVariable long id){
        portfolioService.deletePortfolio(id);
        return "redirect:/";
    }

}
