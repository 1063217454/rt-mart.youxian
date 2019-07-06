package com.product.service.impl;

import com.product.VO.CommodityVo;
import com.product.VO.ProductVO;
import com.product.model.*;
import com.product.repository.*;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BannerInfoRepository bannerInfoRepository;

   @Autowired
    private ProductInfoRepository productInfoRepository;

   @Autowired
   private CommodityRepository commodityRepository;

   @Autowired
   private ProductPicInfoRepository productPicInfoRepository;

   @Autowired
   private ProductCategoryRepository productCategoryRepository;

   @Autowired
   private ProductCommentRepository productCommentRepository;


    @Override
    public List<BannerInfo> bannerShow() {
        List<BannerInfo> infos = bannerInfoRepository.findAll();
        return infos;
    }

    @Override
    public Map<String, Object> commodityList() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        List<Commodity> commodities = commodityRepository.findByCategoryLevel(1);
        if(commodities.size()>0){
            for(int i=0;i<commodities.size();i++){
                Map<String, Object> map = new HashMap<String, Object>();
                Integer id = commodities.get(i).getCommodityId();
                String name = commodities.get(i).getCategoryName();
                Integer commodityId = commodities.get(i).getCommodityId();
                List<Commodity> commodities1 = commodityRepository.findByParentId(commodityId);
                List<CommodityVo> commodityVos = new ArrayList<CommodityVo>();
                if(commodities1.size()>0){
                    for(int j=0;j<commodities1.size();j++){
                        Integer productId = commodities1.get(j).getProduct_id();
                        ProductInfo productInfo = productInfoRepository.findByProductId(productId);
                        List<ProductPicInfo> productPicInfos = productPicInfoRepository.findByProductIdAndAndMasterPic(productId,1);
                        String masterPic = "";
                        if(productPicInfos.size()>0){
                            masterPic = productPicInfos.get(0).getPicUrl();
                        }
                        commodityVos.add(getCommodityVo(masterPic,productInfo));
                    }
                }
                map.put("commodityList",commodityVos);
                map.put("id",id);
                map.put("name",name);
                maps.put("data"+i,map);
            }
            map1.put("result",maps);
            map1.put("message","查询成功");
            map1.put("status","0000");
        }else{
            map1.put("result",maps);
            map1.put("message","查询失败");
            map1.put("status","0001");
        }
        return map1;
    }

    private CommodityVo getCommodityVo(String masterPic,ProductInfo productInfo){
        CommodityVo commodityVo = new CommodityVo();
        commodityVo.setMasterPic(masterPic);
        commodityVo.setPrice(productInfo.getPrice());
        commodityVo.setProductId(productInfo.getProductId());
        commodityVo.setProductName(productInfo.getProductName());
        commodityVo.setSales(productInfo.getSales());
        return commodityVo;
    }

    @Override
    public List<CommodityVo> findCommodityListByLabel(String labelld, Integer page, Integer count) {
        List<CommodityVo> commodityVos = new ArrayList<CommodityVo>();
        //分页查询
        Pageable pageable = PageRequest.of(page-1,count);//从0开始，所以要-1
        Page<ProductInfo> page1 = productInfoRepository.findProductInfoByOneCategoryIdOrTwoCategoryIdPageable(labelld,labelld,pageable);
        List<ProductInfo> productInfos = page1.getContent();
        System.out.println("size="+productInfos.size());
        if(productInfos.size()>0){
            for(int i=0;i<productInfos.size();i++){
                ProductInfo productInfo = productInfos.get(i);
                List<ProductPicInfo> productPicInfos = productPicInfoRepository.findByProductIdAndAndMasterPic(productInfo.getProductId(),1);
                String masterPic = "";
                if(productPicInfos.size()>0){
                    masterPic = productPicInfos.get(0).getPicUrl();
                }

                commodityVos.add(getCommodityVo(masterPic,productInfo));
            }
        }
        return commodityVos;
    }

    @Override
    public Map<String, Object> findCommodityDetailsById(Integer productId) {
        Map<String, Object> map = new HashMap<>();
        ProductVO productVO = new ProductVO();
        ProductInfo productInfo = productInfoRepository.findByProductId(productId);
        if(productInfo != null){
            //ProductCategory
            String categoryName = "";
            Integer categoryId = null;
            List<ProductCategory> productCategories = productCategoryRepository.findByCategoryCode(productInfo.getTwoCategoryId());
            if(productCategories.size()>0){
                categoryName = productCategories.get(0).getCategoryName();
                categoryId = productCategories.get(0).getCategoryId();
            }
            //ProductPicInfo
            String picUrls = "";
            List<ProductPicInfo> productPicInfos = productPicInfoRepository.findByProductId(productId);
            if(productPicInfos.size()>0){
                for(int i=0;i<productPicInfos.size();i++){
                    if(i==0){
                        picUrls = productPicInfos.get(i).getPicUrl();
                    }else{
                        picUrls = picUrls +","+productPicInfos.get(i).getPicUrl();
                    }
                }
            }
            //productComment
            Integer CommentNum = productCommentRepository.findByProductId(productId).size();
            //
            productVO.setCategoryName(categoryName);
            productVO.setCategoryId(categoryId);
            productVO.setDescript(productInfo.getDescript());
            productVO.setPicUrl(picUrls);
            productVO.setPrice(productInfo.getPrice());
            productVO.setSales(productInfo.getSales());
            productVO.setWeight(productInfo.getWeight());
            productVO.setCommentNum(CommentNum);
            productVO.setDetails("");//商品详情 html格式，客户端需要自行解析 notes:先空着
            productVO.setProductName(productInfo.getProductName());
            productVO.setProuuctId(productInfo.getProductId());
            productVO.setStock(productInfo.getStock());
            //
            map.put("result",productVO);
            map.put("message","查询成功");
            map.put("status","0000");
        }else{
            map.put("result",productVO);
            map.put("message","查询失败");
            map.put("status","0001");
        }
        return map;
    }

    @Override
    public Map<String, Object> findCommodityByKeyword(String keyword, Integer page, Integer count) {
        Map<String, Object> map = new HashMap<>();
        List<CommodityVo> commodityVos = new ArrayList<>();

        //分页查询
        Pageable pageable = PageRequest.of(page-1,count);//从0开始，所以要-1
        Page<ProductInfo> page1 = productInfoRepository.findByProductNameLikePageable(keyword,pageable);
        List<ProductInfo> productInfos = page1.getContent();
        System.out.println("size="+productInfos.size());
        if(productInfos.size()>0){
            for(int i=0;i<productInfos.size();i++){
                List<ProductPicInfo> productPicInfos = productPicInfoRepository.findByProductIdAndAndMasterPic(productInfos.get(i).getProductId(),1);
                String masterPic = "";
                if(productPicInfos.size()>0){
                    masterPic = productPicInfos.get(0).getPicUrl();
                }
                commodityVos.add(getCommodityVo(masterPic,productInfos.get(i)));
            }
            map.put("result",commodityVos);
            map.put("message","查询成功");
            map.put("status","0000");
        }else{
            map.put("result",commodityVos);
            map.put("message","查询失败");
            map.put("status","0001");
        }
        return map;
    }

    @Override
    public Map<String, Object> findCommodityByCategory(Integer categoryId, Integer page, Integer count) {
        Map<String, Object> map = new HashMap<>();
        List<CommodityVo> commodityVos = new ArrayList<>();
        ProductCategory productCategory = productCategoryRepository.findByCategoryId(categoryId);
        if(productCategory != null){
            commodityVos = findCommodityListByLabel(productCategory.getCategoryCode(),page,count);
            map.put("result",commodityVos);
            map.put("message","查询成功");
            map.put("status","0000");
        }else{
            map.put("result",commodityVos);
            map.put("message","查询失败");
            map.put("status","0001");
        }
        return map;
    }
}
