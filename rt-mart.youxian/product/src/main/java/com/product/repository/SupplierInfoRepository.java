package com.product.repository;

import com.product.model.SupplierInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierInfoRepository extends JpaRepository<SupplierInfo,Integer> {
    List<SupplierInfo> findBySuplierId(Integer suplierId);
}
