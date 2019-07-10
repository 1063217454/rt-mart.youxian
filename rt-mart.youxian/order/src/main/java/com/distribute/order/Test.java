package com.distribute.order;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String result = "[{\"productId\":3,\"amount\":1},{\"productId\":5,\"amount\":1}]";
        //注意：family中的内容带有中括号[]，所以要转化为JSONArray类型的对象
            //String 转 jsonobject
            //JSONObject Object = JSONObject.fromObject(result);

            //String 转 JSONArray
            /*JSONArray array = JSONArray.fromObject(result);
            for (int i = 0; i < array.size(); i++) {
             //提取出family中的所有
                JSONObject O = JSONObject.fromObject(array.get(i));
                String productId = O.get("productId").toString();
                String amount = O.get("amount").toString();
                System.out.println("productId:" + productId);
                System.out.println("amount:" + amount);
            }*/
    }
    /*
                    JSONObject Object = JSONObject.fromObject(result);
                    customerName = Object.getJSONObject("result").get("customerName").toString();
                    headPicUrl = Object.getJSONObject("result").get("headPicUrl").toString();
                    System.out.println("customerName="+customerName);
                    System.out.println("headPicUrl="+headPicUrl);
                    */

}
