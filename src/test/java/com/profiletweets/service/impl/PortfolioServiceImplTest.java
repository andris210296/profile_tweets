package com.profiletweets.service.impl;

import com.profiletweets.ProfileTweetsTestHelper;
import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.repository.PortfolioRepository;
import com.profiletweets.service.TwitterService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PortfolioServiceImplTest extends ProfileTweetsTestHelper {

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Spy
    private TwitterService twitterService;

    @Test
    public void createPortfolioTest(){

        when(portfolioRepository.save(returnPortfolio())).thenReturn(returnPortfolio());

        Optional<PortfolioOutput> result = portfolioService.createPortfolio(returnPortfolio());

        Assert.assertEquals(NAME,result.get().getName());

    }

    @Test
    public void findAllPortfoliosTest(){

        when(portfolioRepository.findAll()).thenReturn(List.of(returnPortfolio()));

        Optional<List<PortfolioOutput>> result = portfolioService.findAllPortfolios();

        Assert.assertEquals(NAME,result.get().get(0).getName());

    }

    @Test
    public void updatePortfolioTest(){

        Portfolio portfolio = Portfolio.builder().idPortfolio(1l).name(NAME).build();
        when(portfolioRepository.findById(1l)).thenReturn(Optional.of(portfolio));

        Portfolio newPortfolio = Portfolio.builder().idPortfolio(1l).name(NEW_NAME).build();
        when(portfolioRepository.save(portfolio)).thenReturn(portfolio);

        Optional<PortfolioOutput> result = portfolioService.updatePortfolio(1,newPortfolio);

        Assert.assertEquals(NEW_NAME,result.get().getName());

    }

    @Test
    public void deletePortfolioTest(){

        when(portfolioRepository.findById(1l)).thenReturn(Optional.of(returnPortfolioWithId()));

        Optional<PortfolioOutput> result = portfolioService.deletePortfolio(1);

        Assert.assertEquals(null,result.get().getName());

    }
}
