package com.product.repository;

import com.product.model.ProductCategory;
import com.product.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);
}
