package com.mxh.share.entity;

import lombok.Data;

/**
 * author:erhui
 * version:1.0
 **/

@Data
public class Price {
    // 0-8小时费用
    private Double initCost;
    // 8-24小时费用
    private Double baseCost;
    // 24小时后加收费用
    private Double addCost;

}
