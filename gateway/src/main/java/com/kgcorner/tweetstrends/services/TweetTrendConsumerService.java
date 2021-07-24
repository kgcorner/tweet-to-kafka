package com.kgcorner.tweetstrends.services;


import com.kgcorner.tweetstrends.model.Trend;
import com.kgcorner.tweetstrends.model.TweetModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@FeignClient(name="consumerClient", url="${consumer.service.url}")
public interface TweetTrendConsumerService {
    @GetMapping("/tweets")
    ResponseEntity<List<TweetModel>> followTrend(@RequestParam("trend") String trend,
                                                 @RequestParam("page") int page,
                                                 @RequestParam("itemCount") int itemCount);
}