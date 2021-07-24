package com.kgcorner.tweetstrends.services;


import com.kgcorner.tweetstrends.model.Trend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@FeignClient(name="producerClient", url="${producer.service.url}")
public interface TweetTrendMonitorService {

    @PostMapping("/trend")
    ResponseEntity<Trend> createTrendMonitor(@RequestParam("trend" ) String trend);
}