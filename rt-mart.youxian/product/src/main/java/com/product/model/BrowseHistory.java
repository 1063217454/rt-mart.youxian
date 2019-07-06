package com.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 足迹表
 */
@Data
@Entity
public class BrowseHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer browseHistoryId;//用户足迹表ID
    private Integer customerId;
    private Integer productId;
    private Date createTime;
}
