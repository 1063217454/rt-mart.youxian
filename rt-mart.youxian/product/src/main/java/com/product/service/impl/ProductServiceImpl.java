package com.product.service.impl;

import com.product.enums.ProductStatusEnum;
import com.product.model.ProductInfo;
import com.product.repository.ProductInfoRepository;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 查询所有在架商品列表
     */

   @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll(){
        return productInfoRepository.findByPublishStatus(ProductStatusEnum.UP.getCode());
    }
}
