package com.kgcorner.tweetstrends;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@SpringBootApplication
@EnableFeignClients
public class TweetTrendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TweetTrendApplication.class, args);
    }
}