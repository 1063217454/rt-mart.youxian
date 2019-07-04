package com.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by fp
 * 2019-6-28
 */
@Controller
@RequestMapping("/product")
public class ProductController {


    /**
     * 1、banner展示列表
     */
    @GetMapping("/bannerShow")
    public void bannerShow(){

    }

    /**
     * 2、首页商品信息列表
     */
    @GetMapping("/commodityList")
    public void commodityList(){

    }

    /**
     * 3、根据商品列表归属标签查询商品信息
     */
    @GetMapping("/findCommodityListByLabel")
    public void findCommodityListByLabel(){

    }

    /**
     * 4、商品详情
     */
    @GetMapping("/findCommodityDetailsById")
    public void findCommodityDetailsById(){

    }

    /**
     * 5、根据关键词查询商品信息
     */
    @GetMapping("/findCommodityByKeyword")
    public void findCommodityByKeyword(){

    }

    /**
     * 6、根据二级类目查询商品信息
     */
    @GetMapping("/findCommodityByCategory")
    public void findCommodityByCategory(){

    }

    /**
     * 7、我的足迹
     */
    @GetMapping("/browseList")
    public void browseList(){

    }

    /**
     * 8、商品评论列表
     */
    @GetMapping("/CommodityCommentList")
    public void CommodityCommentList(){

    }

    /**
     * 9、商品评论
     */
    @PostMapping("/addCommodityComment")
    public void addCommodityComment(){

    }

    /**
     * 10、查询一级商品类目
     */
    @GetMapping("/findFirstCategory")
    public void findFirstCategory(){

    }

    /**
     * 11、查询二级商品类目
     */
    @GetMapping("/findSecondCategory")
    public void findSecondCategory(){

    }

}
