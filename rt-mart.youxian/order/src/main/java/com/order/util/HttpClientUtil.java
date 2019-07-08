package com.order.util;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 调restful接口用
 */

@Component
public class HttpClientUtil {
    /**
     * post请求传输map数据
     *
     * @param url url地址
     * @param map map数据
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String sendPostDataByMap(String url,Map<String, String> headermap, Map<String, String> map) throws ClientProtocolException, IOException {
        String result = "";
        String encoding = "utf-8";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数 getNameValuePair( map)
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePair( map), encoding));
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //填header参数
        if(headermap!=null){
            for(Map.Entry<String, String> entry : headermap.entrySet()){
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        return result;
    }
    private List<NameValuePair> getNameValuePair(Map<String, String> map){
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return nameValuePairs;
    }

    /**
     * get请求传输数据
     *
     * @param url 请求地址  例如：http://127.0.0.1:8080/test/test?username=zhangsan$username=123456
     * @param headermap 头参数
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String sendGetData(String url,Map<String, String> headermap) throws ClientProtocolException, IOException {
        //普通参数直接塞到url里面去
        String result = "";
        String encoding = "utf-8";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("Content-type", "application/json");
        //塞头参数
        if(headermap != null){
            for(Map.Entry<String, String> entry : headermap.entrySet()){
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }

        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        return result;
    }
}