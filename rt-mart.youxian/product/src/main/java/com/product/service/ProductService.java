package com.product.service;

import com.product.model.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

   /* @Autowired
    private ProductInfoRepository  ppoductInfoRepository;

    @Override
    public List<ProductInfo> findUpAll(){
        return ppoductInfoRepository.findByPublishStatus(ProductStatusEnum.UP.getCode());
    }*/
}
