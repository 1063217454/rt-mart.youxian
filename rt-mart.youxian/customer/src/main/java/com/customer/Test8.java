package com.customer;

import com.customer.util.FastDFSUtil;

import java.io.File;

public class Test8 {
    public static void main(String[] args) {
        FastDFSUtil test = new FastDFSUtil();
        //1、上传图片
        String url = "";
        File file = new File("e:\\test3.jpg");
        long fileLength = file.length();
        try {
             url = test.uploadFile(file, "test3.jpg", fileLength);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("url="+url);

        //2、下载图片
        System.out.println("下载图片");
        try {
        int code = test.downloadFile("group1/M00/00/00/wKhcA10aABuARD-FAAA79NrBhTY227.jpg","e:/test88.jpg");
        String message = code==0?"下载成功":"下载失败"+code;
            System.out.println(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        //3、获取图片信息
        System.out.println("查看图片信息");
        try {
        String message = test.getFileInfo("group1/M00/00/00/wKhcA10aABuARD-FAAA79NrBhTY227.jpg");
            System.out.println("message="+message);
        //文件信息{创建时间: 2019-07-01 20:44:11, 文件长度: 15348B, 文件所上传的服务器ip地址: 192.168.92.3}
        }catch (Exception e){
            e.printStackTrace();
        }

        //4、获取图片Mate信息
       System.out.println("取图片Mate信息");
        try {
            String message = test.getFileMate("group1/M00/00/00/wKhcA10aABuARD-FAAA79NrBhTY227.jpg");
            System.out.println("message="+message);
            //message= 1、fileExtName:jpg 2、fileLength:15348 3、fileName:test3.jpg
        }catch (Exception e){
            e.printStackTrace();
        }

        //5、删除图片
        System.out.println("删除图片");
        try {
            String message = test.deleteFile("group1/M00/00/00/wKhcA10aABuARD-FAAA79NrBhTY227.jpg");
            System.out.println("message="+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
