package com.profiletweets.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.profiletweets.model.twitter.Tweet;
import com.profiletweets.service.TwitterService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TwitterServiceImpl implements TwitterService {
    @Override
    public Optional<List<Tweet>> getTweetsByUserName(String userName) {
        return Optional.of(jsonTweetsToTweets(""));
    }

    private List<Tweet> jsonTweetsToTweets(String json) {

        return Arrays.asList(
                Tweet.builder()
                        .idTwitter(123l)
                        .text("This is my twwweeeeee!")
                        .build(),
                Tweet.builder()
                        .idTwitter(6589l)
                        .text("TLKH LH KJ HJK my twwweeeeee!")
                        .build(),
                Tweet.builder()
                        .idTwitter(83244l)
                        .text("This is my tufisdufksdhfee!")
                        .build());
    }

}
