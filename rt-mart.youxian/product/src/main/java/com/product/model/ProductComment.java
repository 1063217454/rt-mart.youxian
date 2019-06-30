package com.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：商品评论表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */
@Entity
@Data
public class ProductComment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer commentId;//评论ID
    private Integer productId;//商品ID
    private BigInteger orderId;//订单ID
    private String title;//评论标题
    private String content;//评论内容
    private Integer auditStatus;//审核状态：0未审核，1已审核
    private Date auditTime;//评论时间
    private Date modifiedTime;//最后修改时间

}