package com.kgcorner.tweetstrends.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tweet {
    @SerializedName("created_at")
    private Date createdAt;

    private String text;
    private User user;
    @SerializedName("extended_tweet")
    private ExtendedTweet extendedTweet;
}