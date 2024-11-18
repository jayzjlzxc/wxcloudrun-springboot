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

    private String APPID="wx5b307377104d16cd";
    private String APPSECRET="fd6b3ce50f51d25e7dddd7d4cdcae6e5";


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
        return ApiResponse.ok(brandService.getAll());
    }


    @GetMapping("/getPic")
    void getImage(HttpServletResponse response) {
//        try {
//            Resource resource = new ClassPathResource("pic/"+id+".png");
//            File file = resource.getFile();
//            if (file.exists()) {
//                byte[] data = null;
//                FileInputStream input = new FileInputStream(file);
//                data = new byte[input.available()];
//                input.read(data);
//                // 根据文件类型，设置文件Content-Type
//                response.setContentType("image/png");
//                response.getOutputStream().write(data);
//                input.close();
//            }
//        } catch (IOException e) {
//            logger.error("文件不存在");
//        }
    }

        @PostMapping("/update")
    void uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){

        try {
            brandService.update(file, id);
            logger.info("报价更新成功");
        } catch (Exception e) {
            logger.error("报价更新失败");
        }
    }


}
