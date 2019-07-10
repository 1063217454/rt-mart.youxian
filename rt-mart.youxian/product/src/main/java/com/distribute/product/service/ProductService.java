package com.distribute.product.service;

import com.distribute.product.VO.CommodityVo;
import com.distribute.product.model.BannerInfo;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<BannerInfo> bannerShow();

    Map<String ,Object> commodityList();

    List<CommodityVo> findCommodityListByLabel(String labelld, Integer page, Integer count);

    Map<String,Object> findCommodityDetailsById(Integer productId);

    Map<String,Object> findCommodityByKeyword(String keyword,Integer page,Integer count);

    Map<String,Object> findCommodityByCategory(Integer categoryId,Integer page,Integer count);

    Map<String,Object> CommodityCommentList(Integer productId,Integer page,Integer count);

    Map<String,Object> addCommodityComment(Integer customerId, Integer productId, BigInteger orderId, String content, MultipartFile image);

    Map<String,Object> findFirstCategory();

    Map<String,Object> findSecondCategory(Integer categoryId);
}
