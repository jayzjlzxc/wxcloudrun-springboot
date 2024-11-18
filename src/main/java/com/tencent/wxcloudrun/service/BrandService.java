package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Brand;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//@Component
public interface BrandService {

    List<Brand> getAll();
    void addBrand(Brand brand);

    void deleteBrand(Integer id);

    String update(MultipartFile file, Integer id);
}
