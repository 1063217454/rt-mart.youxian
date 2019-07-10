package com.distribute.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 横幅广告类
 */
@Data
@Entity
public class BannerInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer bannerId;
    //图片Url
    private String imageUrl;
    //跳转页面Url
    private String jumpUrl;
    //排序
    private Integer rank;
    //组ID
    private String groupCode;
    //组名称
    private String groupName;

}
