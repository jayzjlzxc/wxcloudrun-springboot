package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BrandController {

    final Logger logger = LoggerFactory.getLogger(BrandController.class);;
    @Autowired
    private BrandService brandService;

    private String APPID="wx15c9d1da24a248ee";
    private String APPSECRET="b3956e69016052ee3bc4251418ab4b98";


//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
//    @PostMapping(value = "/addBrand")
//    ApiResponse addBrand(@RequestBody UserInfo userInfo) {
//        logger.info("login post request");
//        loginService.login(userInfo);
//        return ApiResponse.ok("success");
//    }

    @GetMapping(value = "/getAllBrand")
    ApiResponse getAllBrand() {
        RestTemplate restTemplate = new RestTemplate();
        String filePathUrl = "https://api.weixin.qq.com/tcb/uploadfile?access_token="+getAccessToken();
        //2.1设置请求头
        HttpHeaders filePathHeaders = new HttpHeaders();
        filePathHeaders.setContentType(MediaType.APPLICATION_JSON);
        //2.2设置请求体
        Map<String, Object> filePathRequestData = new HashMap<>();
        filePathRequestData.put("env", "prod-3gch6ubrece736c3");
        filePathRequestData.put("path", "pic/666.png");
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
        ResponseEntity<String> filePathResponseEntity = restTemplate.postForEntity(filePathUrl, filePathRequestEntity, String.class);
        //2.6打印响应数据
        if (filePathResponseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = filePathResponseEntity.getBody();
            if (responseBody != null) {
                System.out.println("上传链接获取成功：" + responseBody);
                return ApiResponse.ok(responseBody);
            } else {
                System.out.println("响应体为空");
                return null;
            }
        } else {
            System.out.println("请求失败：" + filePathResponseEntity.getStatusCode());
            return null;
        }
    }

    public String getAccessToken()
    {
        //1.获取access_token
        RestTemplate restTemplate = new RestTemplate();
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
        ResponseEntity<Map> responseToken = restTemplate.getForEntity(tokenUrl, Map.class);
        String accessToken = (String) responseToken.getBody().get("access_token");
        return accessToken;
    }


//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("env","prod-3gch6ubrece736c3");
//        jsonObject.put("path","pic/666.png");
//        ResponseEntity<String> response = restTemplate.postForEntity("https://api.weixin.qq.com/tcb/uploadfile?access_token=86_4Vtw2uEGQXS9sFO4NeOX1tVGCip8zy1NdANRAmrYOzTi1Q64y5k1yn9-R3j2I65dWbdHIcsoGLPShd5b9l3K46gGfxQrT5QIYTNfKP8F-qOhI9_-O_TndWAoNg0XORfADAOFI", jsonObject, String.class);
//        String body = response.getBody();
//        logger.info("body is "+body);
//        return ApiResponse.ok(body);


    @GetMapping("/image")
    void getImage(@RequestParam("id") Integer id, HttpServletResponse response) {
        try {
            Resource resource = new ClassPathResource("pic/"+id+".png");
            File file = resource.getFile();
            if (file.exists()) {
                byte[] data = null;
                FileInputStream input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                // 根据文件类型，设置文件Content-Type
                response.setContentType("image/png");
                response.getOutputStream().write(data);
                input.close();
            }
        } catch (IOException e) {
            logger.error("文件不存在");
        }
    }

    @PostMapping("/upload")
    void uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){

        String filePath = getClass().getResource("/pic/").getPath();
        try {
            file.transferTo(new File(filePath + id +".png"));
            logger.info("图片上传成功");
        } catch (IOException e) {
            logger.error("上传图片失败");
        }
    }


}
