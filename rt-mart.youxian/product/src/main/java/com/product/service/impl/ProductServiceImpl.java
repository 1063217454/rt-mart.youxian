package com.product.service.impl;

import com.product.model.BannerInfo;
import com.product.repository.BannerInfoRepository;
import com.product.repository.ProductInfoRepository;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BannerInfoRepository bannerInfoRepository;

   @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<BannerInfo> bannerShow() {
        List<BannerInfo> infos = bannerInfoRepository.findAll();
        return infos;
    }
}
