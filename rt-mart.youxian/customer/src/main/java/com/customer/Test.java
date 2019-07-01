package com.customer;

import org.csource.fastdfs.*;

public class Test {
    public String test1(){
        String str = "";
        String configPath = this.getClass().getResource("/fdfs_client.conf").getFile();
        System.out.println("configPath="+configPath);
        try {
            ClientGlobal.init(configPath);
            TrackerClient client = new TrackerClient();
            TrackerServer  trackerServer = client.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            String[] strings = storageClient.upload_file("e:\\test3.jpg","jpg",null);
            for(int i=0;i<strings.length;i++){
                if(i==0){
                    str = strings[i];
                }else{
                    str = str + "/" + strings[i];
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "http://192.168.92.3:8081/" + str;
    }
}
