package com.distribute.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * 便于管理首页商品信息列表而建
 */
@Data
@Entity
public class Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer commodityId;//首页商品信息列表ID

    private Integer product_id;//商品ID

    private String categoryName;//分类名称

    private String categoryCode;//分类编码

    private Integer parentId;//父分类ID

    private Integer categoryLevel;//分类层级

    private Integer categoryStatus;//分类状态

    private Date modifiedTime;//最后修改时间

}
