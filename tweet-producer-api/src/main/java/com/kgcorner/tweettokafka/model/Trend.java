package com.kgcorner.tweettokafka.model;


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
public class Trend {
    private String trendName;
    private Date createdAt;
    private TrendStatus status;
}