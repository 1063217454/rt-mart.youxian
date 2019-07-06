package com.product.repository;

import com.product.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity,Integer> {
    List<Commodity> findByCategoryLevel(Integer categoryLevel);

    List<Commodity> findByParentId(Integer parentId);
}
