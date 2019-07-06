package com.product.repository;

import com.product.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer> {

    List<ProductInfo> findByPublishStatus(Integer publishStatus);

    ProductInfo findByProductId(Integer productId);

    @Query(value = "select pi from ProductInfo pi where pi.oneCategoryId=:oneCategoryId or pi.twoCategoryId=:twoCategoryId")
    Page<ProductInfo> findProductInfoByOneCategoryIdOrTwoCategoryIdPageable(@Param("oneCategoryId") String oneCategoryId,
                                                                            @Param("twoCategoryId") String twoCategoryId,
                                                                            Pageable pageable);

    /*@Query("select a from Area a where a.level = :level and a.name like %:name%")
    List<Area> findByLevelAndNameLike(Integer level,@Param("name") String name);*/

    @Query(value = "select pi from ProductInfo pi where pi.productName like %:productName%")
    Page<ProductInfo> findByProductNameLikePageable(@Param("productName") String productName,Pageable pageable);

}
