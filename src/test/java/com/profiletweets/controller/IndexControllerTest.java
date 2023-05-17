package com.profiletweets.controller;

import com.profiletweets.ProfileTweetsTestHelper;
import com.profiletweets.service.PortfolioService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class IndexControllerTest extends ProfileTweetsTestHelper {

    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private IndexController indexController;

    @Mock
    private Model model;

    @Test
    public void viewHomePageTest(){
        when(portfolioService.findAllPortfolios()).thenReturn(Optional.of(List.of(returnPortfolioOutput())));

        String result = indexController.viewHomePage(model);

        Assert.assertEquals("index", result);
    }

    @Test
    public void showNewPortfolioFormTest(){

        String result = indexController.showNewPortfolioForm(model);

        Assert.assertEquals("newPortfolioForm", result);
    }

    @Test
    public void savePortfolioTest(){

        String result = indexController.savePortfolio(returnPortfolioWithId());

        when(portfolioService.updatePortfolio(returnPortfolioWithId().getIdPortfolio(), returnPortfolio()));

        Assert.assertEquals("redirect:/", result);
    }

    @Test
    public void updatePortfolioFormTest(){

        when(portfolioService.findPortfolio(returnPortfolioWithId().getIdPortfolio())).thenReturn(returnOptionalPortfolioOutput());

        String result = indexController.updatePortfolioForm(returnPortfolioWithId().getIdPortfolio(), model);

        Assert.assertEquals("updatePortfolioForm", result);
    }

    @Test
    public void deletePortfolioTest(){

        when(portfolioService.deletePortfolio(returnPortfolioWithId().getIdPortfolio())).thenReturn(returnOptionalPortfolioOutput());

        String result = indexController.deletePortfolio(returnPortfolioWithId().getIdPortfolio());

        Assert.assertEquals("redirect:/", result);
    }
}
