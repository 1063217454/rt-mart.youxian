package com.product.VO;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Integer productId;
    private Integer customerId;
    private String headPicUrl;
    private String customerName;
    private String content;
    private String image;
    private Date auditTime;
}
