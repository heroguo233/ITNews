package com.gavin.itnews.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Gavin
 * on 2019/4/11 16:46
 */
public class OSSUploadUtils {
    static String bucketName  = "it-news";
    static OSSClient  ossClient;
    public static OSSClient initOOSClient(String endpoint,String accessKeyId,String accessKeySecret){
        ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        return ossClient;
    }

    public static String saveFile(MultipartFile file,String endpoint) throws IOException {
        InputStream inputStream = file.getInputStream();
        String fileName  = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
        ossClient.putObject(bucketName,fileName,inputStream);
        ossClient.shutdown();
//         String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        int index = endpoint.indexOf("//");
        String substring = endpoint.substring(index+2);
//        https://it-news.tp://oss-cn-beijing.aliyuncs.com/d7f1aee27912480287bbc45f11ddee8c娃哈哈.jpg
//        图片处理一下 以height = 80px  width = 100px 的像素格式回显
         String process = "?x-oss-process=image/resize,m_fixed,h_80,w_100";
        return "https://"+bucketName+"."+substring+"/"+fileName+process;
    }

}
