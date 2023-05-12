package com.profiletweets.service;

import com.profiletweets.model.twitter.Tweet;

import java.util.List;
import java.util.Optional;

public interface TwitterService {

    Optional<List<Tweet>> getTweetsByUserName(String userName);
}
