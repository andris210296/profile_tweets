package com.profiletweets.service.impl;

import com.profiletweets.ProfileTweetsTestHelper;
import com.profiletweets.model.twitter.Tweet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TwitterServiceImplTest extends ProfileTweetsTestHelper {

    @Spy
    private TwitterServiceImpl twitterServiceImpl;

    @Test
    public void jsonTweetsToTweetsTest() throws TwitterException {

        ReflectionTestUtils.setField(twitterServiceImpl, "OAuthConsumerKey", "KRy7l0v8wex3w8Sy5zThai3Ea");
        ReflectionTestUtils.setField(twitterServiceImpl, "OAuthConsumerSecret", "X2eBm0Y21kYEuR74W3Frqc2JVIizOj8Q1EVGatDsEVVEJo0ucu");
        ReflectionTestUtils.setField(twitterServiceImpl, "OAuthAccessToken", "1220032047516921859-otvXjhExyUTZ5GLxssc9h5ORqtPZja");
        ReflectionTestUtils.setField(twitterServiceImpl, "OAuthAccessTokenSecret", "tmJKqM4ORfQW6CH7wIVV8uKNpmSEmeFAP8lYwGb19uYjj");

        Optional<List<Tweet>> result = twitterServiceImpl.getTweetsByUserName("Twitter");

        Assert.assertNotNull(result);

    }
}
