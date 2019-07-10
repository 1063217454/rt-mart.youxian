package com.distribute.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：商品分类表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */
@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer categoryId;//分类ID
    private String categoryName;//分类名称
    private String categoryCode;//分类编码
    private Integer parentId;//父分类ID
    private Integer categoryLevel;//分类层级
    private Integer categoryStatus;//分类状态
    private Date modifiedTime;//最后修改时间

}