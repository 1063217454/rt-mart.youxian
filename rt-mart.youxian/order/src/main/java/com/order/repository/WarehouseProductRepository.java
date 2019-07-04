package com.order.repository;

import com.order.model.WarehouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct,Integer> {
    List<WarehouseProduct> findByWpId(Integer wpId);
}
