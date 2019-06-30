package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户积分日志表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerPointLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer pointId;//积分日志ID
    private Integer customerId;//用户ID
    private Integer source;//积分来源：0订单，1登陆，2活动
    private Integer referNumber;//积分来源相关编号
    private Integer changePoint;//变更积分数
    private Date createTime;//积分日志生成时间

}