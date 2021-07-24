package com.kgcorner.tweettokafka.service;


import com.kgcorner.tweettokafka.model.Trend;
import com.kgcorner.tweettokafka.model.TrendStatus;
import com.kgcorner.tweettokafka.producer.TweetProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Service
public class TrendService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Map<String, Trend> trendMap = new ConcurrentHashMap<>();

    public Trend createTrend(String trendName) {
        Trend trend = null;
        if(trendMap.containsKey(trendName)) {
            trend = trendMap.get(trendName);
            if(trend.getStatus() == TrendStatus.PRODUCING) {
                return trend;
            }
        } else {
            trend = new Trend();
            trend.setTrendName(trendName);
            trendMap.put(trendName, trend);
        }
        trend.setCreatedAt(new Date());
        trend.setStatus(TrendStatus.PENDING);
        TweetProducer producer = new TweetProducer(kafkaTemplate);
        producer.produceTweets(trend);
        return trend;
    }
}