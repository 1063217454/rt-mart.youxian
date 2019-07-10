package com.distribute.customer.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 */
@Component
public class JsoupUtil {

    public String getIp() throws IOException{
        Document document = Jsoup.connect("http://200019.ip138.com").get();
        Elements links = document.getElementsByTag("p");
        System.out.println("size = "+links.size());
        String info = links.first().text();
        String ip = info.substring(info.indexOf("[")+1,info.indexOf("]"));
        return ip;
    }

    public String getAdress() throws IOException {
        Document document = Jsoup.connect("http://200019.ip138.com").get();
        Elements links = document.getElementsByTag("p");
        System.out.println("size = "+links.size());
        String info = links.first().text();
        String address = info.substring(info.lastIndexOf("：")+1);
        return address;
    }
    /*public static void main(String[] args) {
		try {
			System.out.println(getIp());
			System.out.println(getAdress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
