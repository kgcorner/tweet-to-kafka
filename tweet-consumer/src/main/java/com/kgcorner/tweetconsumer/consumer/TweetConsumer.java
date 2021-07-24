package com.kgcorner.tweetconsumer.consumer;


import com.kgcorner.tweetconsumer.dao.Persistence;
import com.kgcorner.tweetconsumer.dao.model.TweetModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Component
@Slf4j
public class TweetConsumer {

    @Autowired
    private Persistence persistence;

    @KafkaListener(topics = {"trending-tweets"})
    public void consumeTweets(ConsumerRecord<String, String> consumerRecord) {
        log.info("Received tweet for {}", consumerRecord.key());
        log.info(consumerRecord.value());
        TweetModel tweetModel = new TweetModel();
        tweetModel.setText(consumerRecord.value());
        tweetModel.setTrend(consumerRecord.key());
        persistence.createTweetModel(tweetModel);
    }
}