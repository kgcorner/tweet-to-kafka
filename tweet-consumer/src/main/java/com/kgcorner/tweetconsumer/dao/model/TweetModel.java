package com.kgcorner.tweetconsumer.dao.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Entity
@Table(name="TWEETS")
@NoArgsConstructor
@Data
public class TweetModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name="TWEET")
    private String text;

    @Column(name="TERM")
    private String trend;

}