package com.product.repository;

import com.product.model.BannerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerInfoRepository extends JpaRepository<BannerInfo,Integer> {
    List<BannerInfo> findByBannerId(Integer bannerId);
}
