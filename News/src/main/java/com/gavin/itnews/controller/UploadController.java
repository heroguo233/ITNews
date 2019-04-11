package com.gavin.itnews.controller;

import com.gavin.itnews.utils.OSSUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Gavin
 * on 2019/4/11 16:40
 */
@RestController
public class UploadController {
    final String endpoint ="http://oss-cn-beijing.aliyuncs.com";
    final String accessKeyId = "LTAIUNOPybiWBx8C";
    final String accessKeySecret = "Q75V2Jyu3d3HZbIkK7MaMWQ5bkxiSt";
    @RequestMapping("uploadImage")
    public HashMap<String,Object> uploadImage(MultipartFile file){
        OSSUploadUtils.initOOSClient(endpoint,accessKeyId,accessKeySecret);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",1);
        map.put("msg","上传图片失败");
        try {
            String url = OSSUploadUtils.saveFile(file, endpoint);
            if(url!=null){
                map.put("code",0);
                map.put("msg",url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
