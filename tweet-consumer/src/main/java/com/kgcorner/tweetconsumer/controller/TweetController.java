package com.kgcorner.tweetconsumer.controller;


import com.kgcorner.tweetconsumer.dao.model.TweetModel;
import com.kgcorner.tweetconsumer.service.PersistentTweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@RestController
public class TweetController {

    @Autowired
    private PersistentTweetService service;

    @GetMapping("/tweets")
    public ResponseEntity<List<TweetModel>> getTweets(@RequestParam("trend") String trend,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("itemCount")int itemCount) {
        return ResponseEntity.ok(service.getTweets(trend, page, itemCount));
    }
}