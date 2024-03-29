package com.distribute.product.repository;

import com.distribute.product.model.BrandInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandInfoRepository extends JpaRepository<BrandInfo,Integer> {
    List<BrandInfo> findByBrandId(Integer brandId);
}
