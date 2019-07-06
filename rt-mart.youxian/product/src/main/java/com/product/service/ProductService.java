package com.product.service;

import com.product.VO.CommodityVo;
import com.product.model.BannerInfo;
import com.product.model.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<BannerInfo> bannerShow();

    Map<String ,Object> commodityList();

    List<CommodityVo> findCommodityListByLabel(String labelld, Integer page, Integer count);

    Map<String,Object> findCommodityDetailsById(Integer productId);

    Map<String,Object> findCommodityByKeyword(String keyword,Integer page,Integer count);

    Map<String,Object> findCommodityByCategory(Integer categoryId,Integer page,Integer count);


}
