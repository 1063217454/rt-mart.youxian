package com.product.controller;

import com.product.model.BannerInfo;
import com.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by fp
 * 2019-6-28
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /*
        @ApiOperation(value = "查询用户钱包",notes = "查询用户钱包")
        @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="page",value = "页数（第几页）",paramType = "path",dataType = "Integer"),
                        @ApiImplicitParam(name="count",value = "每页的数据条数",paramType = "path",dataType = "Integer")})*/
    /**
     * 1、banner展示列表
     * @return
     */
    @ApiOperation(value = "banner展示列表",notes = "banner展示列表")
    @GetMapping("/bannerShow")
    public Map<String,Object> bannerShow(){
        Map<String,Object> map = new HashMap<>();
        List<BannerInfo> infos = productService.bannerShow();
        if(infos.size()==0){
            map.put("result",infos);
            map.put("message","查询失败");
            map.put("status","0001");
        }else{
            map.put("result",infos);
            map.put("message","查询成功");
            map.put("status","0000");
        }
        return map;
    }

    /**
     * 2、首页商品信息列表
     * @return
     */
    @ApiOperation(value = "首页商品信息列表",notes = "首页商品信息列表")
    @GetMapping("/commodityList")
    public Map<String,Object> commodityList(){
        Map<String,Object> map = productService.commodityList();
        return map;
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
