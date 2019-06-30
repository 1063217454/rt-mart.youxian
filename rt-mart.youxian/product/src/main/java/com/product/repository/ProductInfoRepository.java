package com.product.repository;

import com.product.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer> {

    List<ProductInfo> findByPublishStatus(Integer publishStatus);
}
