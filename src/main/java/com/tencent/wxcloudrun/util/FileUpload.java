package com.tencent.wxcloudrun.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.controller.BrandController;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileUpload {

    @Autowired
    BrandController controller;
    /**
     * 文件上传
     * @param fileName
     * @param response
     * @param bytes
     * @return
     */
    public static HttpResponse uploadFile(String fileName, Map response, byte[] bytes) {
        String fileUploadUrl = (String) response.get("url");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFilePost = new HttpPost(fileUploadUrl);

        //创建临时文件
        File tempFile = null;
        try {
            tempFile = File.createTempFile(fileName, ".tmp");
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File file = new File("src/main/resources/images/iceberg.jpg");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("key", (WXContent.filePathPre + fileName));
        builder.addTextBody("Signature", (String) response.get("authorization"));
        builder.addTextBody("x-cos-security-token", (String) response.get("token"));
        builder.addTextBody("x-cos-meta-fileid", (String) response.get("cos_file_id"));
//        builder.addPart("file", new FileBody(file));
        builder.addPart("file", new FileBody(tempFile, ContentType.DEFAULT_BINARY));

        HttpEntity multipart = builder.build();
        uploadFilePost.setEntity(multipart);

        try (CloseableHttpResponse uploadResponse = httpClient.execute(uploadFilePost)) {
            int code = uploadResponse.getStatusLine().getStatusCode();
            System.out.println("Response Code: " + code);
            return uploadResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDownloadUrl(Map response){
        String accessToken = FilePath.getAccessToken();
        RestTemplate restTemplate = new RestTemplate();
        String filePathUrl = "https://api.weixin.qq.com/tcb/batchdownloadfile?access_token="+accessToken;
        //2.1设置请求头
        HttpHeaders filePathHeaders = new HttpHeaders();
        filePathHeaders.setContentType(MediaType.APPLICATION_JSON);
        //2.2设置请求体
        Map<String, Object> filePathRequestData = new HashMap<>();
        filePathRequestData.put("env", WXContent.env);
        filePathRequestData.put("file_list", new JSONArray().fluentAdd(new JSONObject().fluentPut("fileid",response.get("file_id")).fluentPut("max_age",1800)));
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
                List fileList = (List) responseBody.get("file_list");
                Map map = (Map) fileList.get(0);
                String downloadUrl = (String) map.get("download_url");
                System.out.println("下载链接获取成功：" + downloadUrl);
                return downloadUrl;
            } else {
                System.out.println("响应体为空");
                return null;
            }
        } else {
            System.out.println("请求失败：" + filePathResponseEntity.getStatusCode());
            return null;
        }

    }

    /**
     * 主方法
     * @param fileName
     * @param bytes
     * @return
     */
    public static String uploadMain(String fileName,byte[] bytes) {
        String accessToken = FilePath.getAccessToken();
        Map response = FilePath.getFilePath(accessToken, fileName);
        if (!response.isEmpty()) {
            HttpResponse httpResponse = FileUpload.uploadFile(fileName, response, bytes);
            if (httpResponse.getStatusLine().getStatusCode() == 204) {
                System.out.println("文件--- " + fileName + " ---上传成功");
                String downloadUrl = FileUpload.getDownloadUrl(response);
                return downloadUrl;
            }else {
                System.out.println("文件--- " + fileName + " ---上传失败");
                return "error";
            }
        } else {
            System.out.println("文件路径获取失败");
            return "error";
        }
    }
    public static byte[] fileToByteArray(File file) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;

        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}