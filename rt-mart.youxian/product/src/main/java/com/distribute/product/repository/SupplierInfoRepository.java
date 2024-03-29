package com.distribute.product.repository;

import com.distribute.product.model.SupplierInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierInfoRepository extends JpaRepository<SupplierInfo,Integer> {
    List<SupplierInfo> findBySupplierId(Integer supplierId);
}
