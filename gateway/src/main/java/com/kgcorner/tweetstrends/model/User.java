package com.kgcorner.tweetstrends.model;


import com.google.gson.annotations.SerializedName;
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
public class User {
    private String name;
    @SerializedName("screen_name")
    private String screenName;
    private String location;
}