package com.product.repository;

import com.product.model.ProductPicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPicInfoRepository extends JpaRepository<ProductPicInfo,Integer> {
    List<ProductPicInfo> findByProductPicId(Integer productPicId);

    List<ProductPicInfo> findByProductIdAndAndMasterPic(Integer productId,Integer masterPic);
}
