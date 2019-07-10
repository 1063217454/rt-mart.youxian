package com.distribute.product.model;

import lombok.Data;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：品牌信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:02:09
 */

@Entity
//@Table(name = "brand_info")
@Data
public class BrandInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer brandId;//品牌ID
    private String brandName;//品牌名称
    private String telephone;//联系电话
    private String brandWeb;//品牌网络
    private String brandLogo;//品牌logo URL
    private String brandDesc;//品牌描述
    private Integer brandStatus;//品牌状态,0禁用,1启用
    private Integer brandOrder;//排序
    private Date modifiedTime;//最后修改时间
}