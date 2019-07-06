package com.product.service;

import com.product.model.BannerInfo;
import com.product.model.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<BannerInfo> bannerShow();

    Map<String ,Object> commodityList();

}
