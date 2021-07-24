package com.kgcorner.tweettokafka.producer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kgcorner.tweettokafka.model.Trend;
import com.kgcorner.tweettokafka.model.TrendStatus;
import com.kgcorner.tweettokafka.model.Tweet;
import com.kgcorner.twitter.consumers.TweetStream;
import com.kgcorner.twitter.consumers.TwitterMessageConsumer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */
@Slf4j
public class TweetProducer {
    private static final int DURATION = 10*1000;
    private static final int PRODUCER_RUN_TIME = 5*30*60*1000;
    private final KafkaTemplate<String, String> template;
    private final Gson gson = new GsonBuilder().setDateFormat("E MMM d H:m:s Z u").create();
    public TweetProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void produceTweets(final Trend trend) {
        TweetStream trendsTweetsStream = TwitterMessageConsumer.getInstance()
            .getTrendsTweetsStream(Collections.singletonList(trend.getTrendName()));
        Thread producerThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                BlockingQueue<String> tweets = trendsTweetsStream.consumeTweets();
                while (trendsTweetsStream.isConnected()) {
                    String tweetStr = tweets.poll(DURATION, TimeUnit.MILLISECONDS);

                    if(tweetStr != null && tweetStr.trim().length() > 1) {
                        Tweet tweet = gson.fromJson(tweetStr, Tweet.class);
                        tweetStr = gson.toJson(tweet);
                        template.sendDefault(trend.getTrendName(), tweetStr);
                    }

                    log.info("Producing for term {}", trend.getTrendName());
                    trend.setStatus(TrendStatus.PRODUCING);
                }
                trend.setStatus(TrendStatus.COMPLETED);
            }
        });
        Thread producerStopperThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(PRODUCER_RUN_TIME);
                trendsTweetsStream.stopConsuming();
                Thread.sleep(5*1000);
                producerThread.stop();
            }
        });
        producerThread.start();
        producerStopperThread.start();
    }
}