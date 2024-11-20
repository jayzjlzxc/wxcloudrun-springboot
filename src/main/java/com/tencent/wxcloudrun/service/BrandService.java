package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONArray;
import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Brand;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//@Component
public interface BrandService {

    List<Brand> getAllBrand();
    void addBrand(Brand brand);

    void deleteBrand(Integer id);

    Boolean update(MultipartFile file, Integer id);

    List<Brand> getAllPrice();
}
