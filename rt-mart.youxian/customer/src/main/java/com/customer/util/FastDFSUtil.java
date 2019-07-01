package com.customer.util;

import org.csource.fastdfs.*;
import org.csource.common.NameValuePair;
import org.springframework.util.Assert;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastDFSUtil {

    /**
     * 上传文件
      * @param file
     * @param uploadFileName
     * @param fileLength
     * @return
     * @throws IOException
     */
        public String uploadFile(File file,String uploadFileName,long fileLength) throws IOException{
            System.out.println("上传文件==========");
            String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
            try{
                ClientGlobal.init(configPath);
            }catch (Exception e){
                e.printStackTrace();
            }
            String url = "";
            byte[] fileBuff = getFileBuffer(new FileInputStream(file),fileLength);
            String[] files = null;
            String fileExtName = "";
            if(uploadFileName.contains(".")){
                fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);//jpg
            }else{
                System.out.println("Fail to upload filr,because the format of filename is illegal.");
                return null;
            }
            //建立连接
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer,storageServer);
            //设置元信息
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName",uploadFileName);
            metaList[1] = new NameValuePair("fileExtName",fileExtName);
            metaList[2] = new NameValuePair("fileLength",String.valueOf(fileLength));
            //上传文件
            try {
                files = client.upload_file(fileBuff,fileExtName,metaList);
                for(int i=0;i<files.length;i++){
                    if(i==0){
                        url = files[i];
                    }else{
                        url = url + "/" + files[i];
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "http://192.168.92.3:8081/" + url;
        }

        private static byte[] getFileBuffer(InputStream inputStream,long fileLength) throws IOException {
            byte[] buffer = new byte[256*1024];
            byte[] fileBuffer = new byte[(int)fileLength];
            int count = 0;
            int length = 0;
            while((length = inputStream.read(buffer)) != -1){
                for (int i=0;i<length;++i){
                    fileBuffer[count + i] = buffer[i];
                }
                count += length;
            }
            return fileBuffer;
        }

    /**
     * 下载文件
     * @param file_id
     * @param local_filename
     * @throws Exception
     */
    public int downloadFile(String file_id,String local_filename) throws Exception{
        System.out.println("下载文件==========");
        String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
        try{
            ClientGlobal.init(configPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer,storageServer);
        int code = storageClient.download_file1(file_id, local_filename);
        return code;
       /* System.out.println("b="+b);
        System.out.println("文件大小："+b.length);
        String fileName = "e:\\test88.jpg";
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(b);
        out.flush();
        out.close();*/
    }

    /**
     * 查看文件信息
     * @param fileId
     * @throws Exception
     */
    public String getFileInfo(String fileId) throws Exception{
        System.out.println("获取文件信息==========");
        String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
        try{
            ClientGlobal.init(configPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer,storageServer);
        FileInfo fileInfo = storageClient.get_file_info1(fileId);

        Date createDate = fileInfo.getCreateTimestamp();
        long fileSize = fileInfo.getFileSize();
        String ip = fileInfo.getSourceIpAddr();
        String message = "文件信息{创建时间: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createDate)+
                ", 文件长度: "+fileSize+"B, 文件所上传的服务器ip地址: "+ip+"}";
        return message;
    }

    /**
     * 获取文件Mate
     * @param fileID
     * @throws Exception
     */
    public String getFileMate(String fileID) throws Exception{
        System.out.println("获取文件Mate==========");
        String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
        try{
            ClientGlobal.init(configPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer,storageServer);
        NameValuePair nvps[] = storageClient.get_metadata1(fileID);
        String message = "";
        for(int i=0;i<nvps.length;i++){
            if(i==0){
                message = "1、" + nvps[i].getName()+":"+URLDecoder.decode(nvps[i].getValue(),"UTF-8");
            }else{
                int i1 = i+1;
                message = message +i1 + "、" + nvps[i].getName()+":"+URLDecoder.decode(nvps[i].getValue(),"UTF-8");
            }
        }
        return message;
    }

    /**
     * 删除文件
     * @param fileID
     * @throws Exception
     */
    public String deleteFile(String fileID) throws Exception{
        System.out.println("删除文件==========");
        String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
        try{
            ClientGlobal.init(configPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer,storageServer);
        int i = storageClient.delete_file1(fileID);
        return i==0?"删除成功":"删除失败："+i;
    }
}
