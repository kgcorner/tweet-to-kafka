package com.kgcorner.tweetstrends.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TweetModel {
    private int id;
    private String text;
    private String trend;
}