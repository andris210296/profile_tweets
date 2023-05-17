package com.profiletweets.model;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.model.twitter.Tweet;

import java.util.List;

public class PortfolioUtils {

    public static PortfolioOutput portfolioToPortfolioOutput(Portfolio portfolio, List<Tweet> tweets){
        return PortfolioOutput.builder()
                .idPortfolio(portfolio.getIdPortfolio())
                .imagePath(portfolio.getImagePath())
                .name(portfolio.getName())
                .experience(portfolio.getExperience())
                .twitterUserName(portfolio.getTwitterUserName())
                .tweets(tweets)
                .build();
    }

    public static String pathCompleteToSmall(String pathString){
        pathString = pathString.replace("\\","/");
        String[] pathSplited = pathString.split("/");

        return "/" + pathSplited[pathSplited.length -2] + "/" + pathSplited[pathSplited.length -1];
    }
}
