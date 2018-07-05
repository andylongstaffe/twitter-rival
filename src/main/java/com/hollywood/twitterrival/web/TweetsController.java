package com.hollywood.twitterrival.web;

import com.hollywood.twitterrival.model.Tweet;
import com.hollywood.twitterrival.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//    /tweets
//
//    /tweets/12
//
//    /users
//
//    /users?screenname=andylongstaffe
//
//    /links
@RestController
@RequestMapping("/tweets")
public class TweetsController {

    @Autowired
    private TweetService service;

    @GetMapping("/{tweetId}")
    public ResponseEntity<Tweet> getTweet(@PathVariable long tweetId) {
        Tweet result = service.getTweet(tweetId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        return new ResponseEntity<>(service.getAllTweets(), HttpStatus.OK);
    }

}
