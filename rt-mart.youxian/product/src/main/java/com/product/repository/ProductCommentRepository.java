package com.product.repository;

import com.product.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Integer> {
    List<ProductComment> findByCommentId(Integer commentId);
}
