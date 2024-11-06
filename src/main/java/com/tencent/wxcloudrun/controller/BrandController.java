package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class BrandController {

    final Logger logger = LoggerFactory.getLogger(BrandController.class);;
    @Autowired
    private BrandService brandService;
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
