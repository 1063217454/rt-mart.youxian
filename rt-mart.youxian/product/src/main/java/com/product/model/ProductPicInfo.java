package com.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：商品图片信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */
@Entity
@Data
public class ProductPicInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer productPicId;//商品图片ID
    private Integer product_id;//商品ID
    private String picDesc;//图片描述
    private String picUrl;//图片URL
    private Integer isMaster;//是否主图：0.非主图1.主图
    private Integer picOrder;//图片排序
    private Integer picStatus;//图片是否有效：0无效 1有效
    private Date modifiedTime;//最后修改时间

}