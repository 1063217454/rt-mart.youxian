package com.distribute.customer.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerLoginVO {

    @JsonProperty("customerid")
    private Integer customerId;

    @JsonProperty("loginName")
    private String loginName;

    @JsonProperty("userStats")
    private Integer userStats;
}
