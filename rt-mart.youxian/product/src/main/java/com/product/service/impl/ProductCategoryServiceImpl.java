package com.product.service.impl;

import com.product.model.ProductCategory;
import com.product.repository.ProductCategoryRepository;
import com.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryIdIn(categoryTypeList);

        //categoryTypeList : Arrays.asList(11,12)
    }
}
