package com.distribute.product.repository;

import com.distribute.product.model.BannerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerInfoRepository extends JpaRepository<BannerInfo,Integer> {
    List<BannerInfo> findByBannerId(Integer bannerId);

    List<BannerInfo> findAll();
}
