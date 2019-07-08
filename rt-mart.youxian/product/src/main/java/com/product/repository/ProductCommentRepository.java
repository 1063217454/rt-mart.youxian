package com.product.repository;

import com.product.model.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Integer> {
    List<ProductComment> findByCommentId(Integer commentId);

    List<ProductComment> findByProductId(Integer productId);

    //只展示审核过的评论
    @Query(value = "select pc from ProductComment pc where pc.productId=:productId and pc.auditStatus=1")
    Page<ProductComment> findByProductIdPageable(@Param("productId") Integer productId, Pageable pageable);

}
