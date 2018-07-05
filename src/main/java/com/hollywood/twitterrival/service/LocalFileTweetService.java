package com.hollywood.twitterrival.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hollywood.twitterrival.model.Tweet;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LocalFileTweetService implements TweetService {

    Tweet[] tweets;
    Gson gson;

    public LocalFileTweetService() {
        gson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
        try {
            tweets = gson.fromJson(new FileReader("src/main/resources/tweets.json"), Tweet[].class);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("JSON file not found");
        }
    }

    @Override
    public Tweet getTweet(long id) {
        for ( Tweet tweet : tweets ) {
            if ( tweet.getId().equals(id) ) {
                return tweet;
            }
        }
        System.out.println("Tweet " + id + " not found");
        return null;
    }

    @Override
    public List<Tweet> getAllTweets() {
        return Arrays.asList(tweets);
    }
}
