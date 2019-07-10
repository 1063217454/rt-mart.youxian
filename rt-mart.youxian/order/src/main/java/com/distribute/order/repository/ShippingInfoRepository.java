package com.distribute.order.repository;

import com.distribute.order.model.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo,Integer> {
    List<ShippingInfo> findByShipId(Integer shipId);
}
