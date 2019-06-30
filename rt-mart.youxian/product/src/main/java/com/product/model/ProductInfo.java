package com.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：商品信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */
@Entity
@Data
public class ProductInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer productId;//主键ID
    private char productCore;//商品编码
    private String productName;//商品名称
    private String barCode;//国条码
    private Integer oneCategoryId;//一级分类ID
    private Integer twoCategoryId;//二级分类ID
    private Integer threeCategoryId;//三级分类ID
    private  Integer supplierId;//商品供应商ID
    private BigDecimal price;//商品销售价格
    private BigDecimal averageCost;//商品加权平均成本
    private Integer publishStatus;//上下架状态：0下架1上架
    private Integer auditStatus;//审核状态：0未审核，1已审核
    private float weight;//商品重量
    private float length;//商品长度
    private float height;//商品高度
    private float width;//商品宽度
    private char colorType;//'红','黄','蓝','黑'
    private Date productionDate;//生产日期
    private int shelfLife;//商品有效期
    private String descript;//商品描述
    private Date indate;//商品录入时间
    private Date modifiedTime;//最后修改时间
}