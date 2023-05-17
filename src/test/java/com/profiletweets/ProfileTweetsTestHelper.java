package com.profiletweets;

import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.model.twitter.Tweet;
import twitter4j.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProfileTweetsTestHelper {

    protected static final String NAME = "name";
    protected static final String NEW_NAME = "new name";

    protected static final String ID_PORTFOLIO = "idPortfolio";

    protected Portfolio returnPortfolio(){
        return Portfolio.builder().name(NAME).build();
    }

    protected Portfolio returnPortfolioWithId(){
        return Portfolio.builder().idPortfolio(Long.valueOf(1)).name(NAME).build();
    }

    protected PortfolioOutput returnPortfolioOutput(){
        return PortfolioOutput.builder().idPortfolio(Long.valueOf(1)).name(NAME).build();
    }

    protected Optional<PortfolioOutput> returnOptionalPortfolioOutput(){
        return Optional.of(PortfolioOutput.builder().idPortfolio(1l).name(NAME).build());
    }

    protected HashMap<String, Object> generateHashMapPortfolioWithId() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(ID_PORTFOLIO, 1);
        hashMap.put(NAME, NEW_NAME);
        return hashMap;
    }
    protected HashMap<String, String> generateHashMapPortfolioWithoutId() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(NAME, NAME);
        return hashMap;
    }

    protected List<Tweet> generateListTweets() {
        return List.of(Tweet.builder().idTwitter(1l).text(NAME).build());
    }
}
