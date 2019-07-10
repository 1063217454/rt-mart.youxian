package com.distribute.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户级别信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerLevelInf {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer customerLevel;//会员级别ID
    private String levelName;//会员级别名称
    private Integer minPoint;//该级别最低积分
    private Integer maxPoint;//该级别最高积分
    private Date modifiedTime;//最后修改时间

}