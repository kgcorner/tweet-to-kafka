package com.kgcorner.tweettokafka.controller;


import com.kgcorner.tweettokafka.model.Trend;
import com.kgcorner.tweettokafka.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@RestController
public class TrendController {

    @Autowired
    private TrendService trendService;

    @PostMapping("/trend")
    public ResponseEntity<Trend> createTrendToMonitor(@RequestParam("trend") String trend) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trendService.createTrend(trend));
    }

}