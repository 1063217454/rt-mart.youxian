package com.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：供应商信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */
@Entity
@Data
public class SupplierInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer supplierId;//供应商ID
    private Character supplierCode;//供应商编码
    private Character supplierName;//供应商名称
    private Integer supplierType;//供应商类型：1.自营，2.平台
    private String linkMan;//供应商联系人
    private String phoneNumber;//联系电话
    private String bankName;//供应商开户银行名称
    private String bankAccount;//银行账号
    private String address;//供应商地址
    private Integer supplierStatus;//状态：0禁止，1启用
    private Date modifiedTime;//最后修改时间

}