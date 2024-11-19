package com.tencent.wxcloudrun.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    /**
     * 获取accessToken
     * @return
     */
    public static String getAccessToken()
    {
        //1.获取access_token
        RestTemplate restTemplate = new RestTemplate();
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WXContent.APPID + "&secret=" + WXContent.APPSECRET;
        ResponseEntity<Map> responseToken = restTemplate.getForEntity(tokenUrl, Map.class);
        String accessToken = (String) responseToken.getBody().get("access_token");
        return accessToken;
    }

    /**
     * 获取文件上传链接
     * @param accessToken
     * @param fileName
     * @return
     */
    public static Map getFilePath(String accessToken,String fileName)
    {
        RestTemplate restTemplate = new RestTemplate();
        String filePathUrl = "https://api.weixin.qq.com/tcb/uploadfile?access_token=" + accessToken;
        //2.1设置请求头
        HttpHeaders filePathHeaders = new HttpHeaders();
        filePathHeaders.setContentType(MediaType.APPLICATION_JSON);
        //2.2设置请求体
        Map<String, Object> filePathRequestData = new HashMap<>();
        filePathRequestData.put("env", WXContent.env);
        filePathRequestData.put("path", WXContent.filePathPre + fileName);
        //2.3将请求体转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String filePathRequestDataToJSON = "";
        try {
            filePathRequestDataToJSON = objectMapper.writeValueAsString(filePathRequestData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //2.4创建请求实体
        org.springframework.http.HttpEntity<String> filePathRequestEntity = new org.springframework.http.HttpEntity<>(filePathRequestDataToJSON, filePathHeaders);
        System.out.println(filePathRequestEntity);
        //2.5发送请求并获取响应
        ResponseEntity<Map> filePathResponseEntity = restTemplate.postForEntity(filePathUrl, filePathRequestEntity, Map.class);
        //2.6打印响应数据
        if (filePathResponseEntity.getStatusCode().is2xxSuccessful()) {
            Map responseBody = filePathResponseEntity.getBody();
            if (responseBody != null) {
                System.out.println("上传链接获取成功：" + responseBody);
                return responseBody;
            } else {
                System.out.println("响应体为空");
                return null;
            }
        } else {
            System.out.println("请求失败：" + filePathResponseEntity.getStatusCode());
            return null;
        }
    }
}