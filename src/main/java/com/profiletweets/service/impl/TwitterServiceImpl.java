package com.profiletweets.service.impl;

import com.profiletweets.model.twitter.Tweet;
import com.profiletweets.service.TwitterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TwitterServiceImpl implements TwitterService {

    @Value("${twitter.OAuthConsumerKey:KRy7l0v8wex3w8Sy5zThai3Ea}")
    private String OAuthConsumerKey;

    @Value("${twitter.OAuthConsumerSecret:X2eBm0Y21kYEuR74W3Frqc2JVIizOj8Q1EVGatDsEVVEJo0ucu}")
    private String OAuthConsumerSecret;

    @Value("${twitter.OAuthAccessToken:1220032047516921859-otvXjhExyUTZ5GLxssc9h5ORqtPZja}")
    private String OAuthAccessToken;

    @Value("${twitter.OAuthAccessTokenSecret:tmJKqM4ORfQW6CH7wIVV8uKNpmSEmeFAP8lYwGb19uYjj}")
    private String OAuthAccessTokenSecret;

    @Override
    public Optional<List<Tweet>> getTweetsByUserName(String userName){

        List<Status> status = callTwitterAPI(userName);

        return Optional.of(status.stream().map(twitter->{

            return Tweet.builder()
                    .idTwitter(twitter.getId())
                    .text(twitter.getText())
                    .imageUrl(twitter.getUser().getProfileImageURL())
                    .build();

        }).collect(Collectors.toList()));
    }

    private List<Status> callTwitterAPI(String userName){

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(OAuthConsumerKey)
                .setOAuthConsumerSecret(OAuthConsumerSecret)
                .setOAuthAccessToken(OAuthAccessToken)
                .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();

        Paging page = new Paging(1, 100);

        try {
            return twitter.getUserTimeline(userName, page).stream().limit(5).toList();
        }catch (TwitterException e){
            return new ArrayList<>();
        }
    }
}
