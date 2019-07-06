package com.product.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 测试调用restful接口
 */
public class HttpClientTest {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/customer/getCustomerById");

            httpGet.setHeader("customerId","4");
            httpGet.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            HttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            System.out.println("entity="+entity);
            String result = new BufferedReader(new InputStreamReader(entity.getContent())).lines().collect(Collectors.joining("\n"));
            System.out.println("result="+result);
            //转换
            JSONObject Object = JSONObject.fromObject(result);
            String customerName = Object.getJSONObject("result").get("customerName").toString();
            String headPicUrl = Object.getJSONObject("result").get("headPicUrl").toString();
            System.out.println("customerName="+customerName);
            System.out.println("headPicUrl="+headPicUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
