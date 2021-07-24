package com.kgcorner.twitter.consumers;


import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.StatsReporter;

import java.util.concurrent.BlockingQueue;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 20/07/21
 */

public class TweetStream {
    private Client twitterClient;
    private BlockingQueue<String> tweetQueue;

    public TweetStream(Client twitterClient, BlockingQueue<String> tweetQueue) {
        this.twitterClient = twitterClient;
        this.tweetQueue = tweetQueue;
    }

    public BlockingQueue<String> consumeTweets() {
        if(twitterClient != null) {
            twitterClient.connect();
            return tweetQueue;
        } else {
            throw new IllegalStateException("Client is null");
        }
    }
    public void stopConsuming() {
        if(twitterClient != null) {
            twitterClient.stop();
        }
    }
    public boolean isConnected() {
        return !twitterClient.isDone();
    }
}