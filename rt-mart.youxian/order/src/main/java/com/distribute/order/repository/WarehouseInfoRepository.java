package com.distribute.order.repository;

import com.distribute.order.model.WarehouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseInfoRepository extends JpaRepository<WarehouseInfo,Integer> {
    List<WarehouseInfo> findByWId(Integer wId);
}
