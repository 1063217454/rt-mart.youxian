package com.distribute.product.repository;

import com.distribute.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);

    List<ProductCategory> findByCategoryCode(String categoryCode);

    ProductCategory findByCategoryId(Integer categoryId);

    List<ProductCategory> findByCategoryLevelAndParentId(Integer categoryLevel,Integer parentId);

}
