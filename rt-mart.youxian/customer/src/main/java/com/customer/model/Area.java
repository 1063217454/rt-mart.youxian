package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 全国省市区县街道id代码表
 */
@Entity
@Data
public class Area {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer areaId;//自增主键
    private String code;//地区编号
    private String parent;//上一级code
    private String name;//名称
    private Integer level;//共4个等级：1：省 2：市 3：区 4：街道
    private Integer isLast;//是不是最后一级
}
