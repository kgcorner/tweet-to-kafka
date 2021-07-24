package com.kgcorner.tweetstrends.controller;


import com.kgcorner.tweetstrends.model.Trend;
import com.kgcorner.tweetstrends.model.TweetModel;
import com.kgcorner.tweetstrends.services.TweetTrendConsumerService;
import com.kgcorner.tweetstrends.services.TweetTrendMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private TweetTrendMonitorService tweetTrendService;

    @Autowired
    private TweetTrendConsumerService tweetTrendConsumerService;

    @PostMapping("/trend")
    public ResponseEntity<Trend> createTrendMonitor(@RequestParam("trendName") String trendName) {
        return tweetTrendService.createTrendMonitor(trendName);
    }

    @GetMapping("/trend")
    public ResponseEntity<List<TweetModel>> followTrend(@RequestParam("trend") String trend,
                                                        @RequestParam("page") int page,
                                                        @RequestParam("itemCount") int itemCount) {
        return tweetTrendConsumerService.followTrend(trend, page, itemCount);
    }
}