package com.product.service.impl;

import com.product.VO.CommodityVo;
import com.product.model.BannerInfo;
import com.product.model.Commodity;
import com.product.model.ProductInfo;
import com.product.model.ProductPicInfo;
import com.product.repository.BannerInfoRepository;
import com.product.repository.CommodityRepository;
import com.product.repository.ProductInfoRepository;
import com.product.repository.ProductPicInfoRepository;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public List<BannerInfo> bannerShow() {
        List<BannerInfo> infos = bannerInfoRepository.findAll();
        return infos;
    }

    @Override
    public Map<String, Object> commodityList() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();

        List<Commodity> commodities = commodityRepository.findByCategoryLevel(1);
        if(commodities.size()>0){
            for(int i=0;i<commodities.size();i++){
                Integer id = commodities.get(i).getCommodityId();
                String name = commodities.get(i).getCategoryName();
                Integer commodityId = commodities.get(i).getCommodityId();
                List<Commodity> commodities1 = commodityRepository.findByParentId(commodityId);
                List<CommodityVo> commodityVos = new ArrayList<CommodityVo>();
                if(commodities1.size()>0){
                    for(int j=0;j<commodities1.size();j++){
                        Integer productId = commodities1.get(j).getProduct_id();
                        List<ProductInfo> productInfo = productInfoRepository.findByProductId(productId);
                        List<ProductPicInfo> productPicInfos = productPicInfoRepository.findByProductIdAndAndMasterPic(productId,1);
                        String masterPic = "";
                        if(productPicInfos.size()>0){
                            masterPic = productPicInfos.get(0).getPicUrl();
                        }
                        CommodityVo commodityVo = new CommodityVo();
                        commodityVo.setMasterPic(masterPic);
                        commodityVo.setPrice(productInfo.get(0).getPrice());
                        commodityVo.setProductId(productId);
                        commodityVo.setProductName(productInfo.get(0).getProductName());
                        commodityVo.setSales(productInfo.get(0).getSales());
                        commodityVos.add(commodityVo);
                    }
                }
                map.put("data"+i,commodityVos);
                map.put("id",id);
                map.put("name",name);
            }
            map1.put("result",map);
            map1.put("message","查询成功");
            map1.put("status","0000");
        }else{
            map1.put("result",map);
            map1.put("message","查询失败");
            map1.put("status","0001");
        }
        return map1;
    }
}
