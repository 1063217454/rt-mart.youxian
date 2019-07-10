package com.distribute.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryVO {
    @JsonProperty("id")
    private Integer categoryId;//类目id
    @JsonProperty("name")
    private String categoryName;//分类名称
}
