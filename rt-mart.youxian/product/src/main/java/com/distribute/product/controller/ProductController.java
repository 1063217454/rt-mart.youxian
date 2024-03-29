package com.distribute.product.controller;

import com.distribute.product.VO.CommodityVo;
import com.distribute.product.model.BannerInfo;
import com.distribute.product.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
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
    @ApiOperation(value = "根据商品列表归属标签查询商品信息",notes = "根据商品列表归属标签查询商品信息")
    @ApiImplicitParams({@ApiImplicitParam(name="labelld",value = "标签ID",paramType = "path",dataType = "String"),
            @ApiImplicitParam(name="page",value = "页数（第几页）",paramType = "path",dataType = "Integer"),
            @ApiImplicitParam(name="count",value = "每页的数据条数",paramType = "path",dataType = "Integer")})
    @GetMapping("/findCommodityListByLabel")
    public Map<String,Object> findCommodityListByLabel(@RequestParam(name = "labelld") String labelld,
                                         @RequestParam(name = "page") Integer page,
                                         @RequestParam(name = "count") Integer count){
        Map<String,Object> map =new HashMap<String,Object>();
        List<CommodityVo> commodityVos = new ArrayList<CommodityVo>();
        if(labelld.length()==3 || labelld.length()==6){
            commodityVos = productService.findCommodityListByLabel(labelld,page,count);
            map.put("result",commodityVos);
            map.put("message","查询成功");
            map.put("status","0000");
        }else{
            map.put("result",commodityVos);
            map.put("message","查询失败，标签ID有误");
            map.put("status","0001");
        }
        return map;
    }

    /**
     * 4、商品详情
     */
    @GetMapping("/findCommodityDetailsById")
    public Map<String, Object> findCommodityDetailsById(@RequestHeader(name = "customerId") Integer customerId,
                                         @RequestParam(name = "productId") Integer productId){
        Map<String, Object> map = productService.findCommodityDetailsById(productId);
        return map;
    }

    /**
     * 5、根据关键词查询商品信息
     */
    @GetMapping("/findCommodityByKeyword")
    public Map<String, Object> findCommodityByKeyword(@RequestParam(name = "keyword") String keyword,
                                       @RequestParam(name = "page") Integer page,
                                       @RequestParam(name = "count") Integer count){

        Map<String, Object> map = productService.findCommodityByKeyword(keyword,page,count);
        return map;
    }

    /**
     * 6、根据二级类目查询商品信息
     */
    @GetMapping("/findCommodityByCategory")
    public Map<String, Object> findCommodityByCategory(@RequestParam(name = "categoryId")Integer categoryId,
                                        @RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "count") Integer count){
        Map<String, Object> map = productService.findCommodityByCategory(categoryId,page,count);
        return map;
    }

    /**
     * 7、我的足迹 notis:暂时无此功能,不想搞
     */
    @GetMapping("/browseList")
    public void browseList(@RequestHeader(name = "customerId") Integer customerId,
                           @RequestParam(name = "page") Integer page,
                           @RequestParam(name = "count") Integer count){
    }

    /**
     * 8、商品评论列表
     */
    @GetMapping("/CommodityCommentList")
    public Map<String, Object> CommodityCommentList(@RequestParam(name = "productId") Integer productId,
                                                    @RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "count") Integer count){
        Map<String, Object> map = productService.CommodityCommentList(productId,page,count);
        return map;
    }

    /**
     * 9、商品评论
     */
    @PostMapping("/addCommodityComment")
    public Map<String,Object> addCommodityComment(@RequestHeader(name = "customerId")Integer customerId,
                                    @RequestParam(name = "productId")Integer productId,
                                    @RequestParam(name = "orderId") BigInteger orderId,
                                    @RequestParam(name = "content")String content,
                                    @RequestParam(name = "file") MultipartFile file){
        Map<String,Object> map = productService.addCommodityComment(customerId,productId,orderId,content,file);
        return map;
    }

    /**
     * 10、查询一级商品类目
     */
    @GetMapping("/findFirstCategory")
    public Map<String,Object> findFirstCategory(){
        Map<String,Object> map = productService.findFirstCategory();
        return map;
    }

    /**
     * 11、查询二级商品类目
     */
    @GetMapping("/findSecondCategory")
    public Map<String,Object> findSecondCategory(@RequestParam(name = "categoryId")Integer categoryId){
        Map<String,Object> map = productService.findSecondCategory(categoryId);
        return map;
    }

}
