package com.distribute.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：仓库信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class WarehouseInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer wId;//仓库ID
    private Character warehouseSn;//仓库编码
    private String warehoustName;//仓库名称
    private String warehousePhone;//仓库电话
    private String contact;//仓库联系人
    private Integer province;//省
    private Integer city;//市
    private Integer distrct;//区
    private String address;//仓库地址
    private Integer warehouseStatus;//仓库状态：0禁用，1启用
    private Date modifiedTime;//最后修改时间

}