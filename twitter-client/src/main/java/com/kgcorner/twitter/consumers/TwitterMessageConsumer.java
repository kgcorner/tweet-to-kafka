package com.kgcorner.twitter.consumers;


import com.kgcorner.twitter.client.TwitterClient;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 18/07/21
 */

public class TwitterMessageConsumer {

    private static TwitterMessageConsumer INSTANCE = null;

    public static TwitterMessageConsumer getInstance() {
        if(INSTANCE == null) {
            TwitterClient.initClient();
            INSTANCE = new TwitterMessageConsumer();
        }
        return INSTANCE;
    }

    public List<String> consumeTweets(List<String> terms, int maxTweetCount, long timeout) {
        List<String> tweetsList = new ArrayList<>();
        BlockingQueue<String> tweetsQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Event> eventBlockingQueue = new LinkedBlockingQueue<>();
        Client client = TwitterClient.createClient(tweetsQueue, terms, eventBlockingQueue);
        client.connect();
        int count = 0;
        while (!client.isDone()) {
            try {
                String tweet = tweetsQueue.poll(timeout, TimeUnit.MILLISECONDS);
                if(tweet == null || tweet.trim().length() == 0) {
                    client.stop();
                }
                tweetsList.add(tweet);
                count++;
                if(count == maxTweetCount) {
                    client.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }
        return tweetsList;
    }

    public TweetStream getTrendsTweetsStream(List<String> terms) {
        BlockingQueue<String> tweetsQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Event> eventBlockingQueue = new LinkedBlockingQueue<>();
        Client client = TwitterClient.createClient(tweetsQueue, terms, eventBlockingQueue);
        TweetStream stream = new TweetStream(client, tweetsQueue);
        return stream;
    }
}