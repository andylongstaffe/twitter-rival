package com.hollywood.twitterrival.service;

import com.hollywood.twitterrival.model.Tweet;

import java.util.List;

public interface TweetService {

    Tweet getTweet(long id);

    List<Tweet> getAllTweets();

}
