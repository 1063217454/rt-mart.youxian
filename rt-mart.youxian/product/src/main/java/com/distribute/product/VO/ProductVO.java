package com.distribute.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.jdbc.util.BaseBugReport;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Integer prouuctId;//商品ID
    private  String productName;//商品名称
    private String descript;//商品描述
    private String picUrl;//商品图片地址，以逗号间隔
    private Integer stock;//库存
    private String details;//商品详情 html格式，客户端需要自行解析
    private Integer commentNum;//评论数
    private BigDecimal price;//价格
    private Integer sales;//销量
    private Float weight;//重量
    private Integer categoryId;//所属二级类目ID
    private String categoryName;//类目名称
}
