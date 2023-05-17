package com.profiletweets.model;

import com.profiletweets.ProfileTweetsTestHelper;
import com.profiletweets.model.portfolio.PortfolioOutput;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PortfolioUtilsTest extends ProfileTweetsTestHelper {

    @Test
    public void portfolioToPortfolioOutput(){
        PortfolioOutput result = PortfolioUtils.portfolioToPortfolioOutput(returnPortfolio(),generateListTweets());

        Assert.assertEquals(generateListTweets().get(0).getIdTwitter(), result.getTweets().get(0).getIdTwitter() );
    }

    @Test
    public void pathCompleteToSmallTest(){
        String complete = "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\webapp\\photos\\car.jpg";

        String result = PortfolioUtils.pathCompleteToSmall(complete);

        Assert.assertEquals("/photos/car.jpg", result);

    }
}
