package com.kgcorner.tweetconsumer.service;


import com.kgcorner.tweetconsumer.dao.Persistence;
import com.kgcorner.tweetconsumer.dao.model.TweetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Service
public class PersistentTweetService {

    @Autowired
    private Persistence persistence;

    public List<TweetModel> getTweets(String term, int page, int itemCount) {
        int offset = (page -1 ) * itemCount + 1;
        return persistence.getTweets(term, offset, itemCount);
    }
}