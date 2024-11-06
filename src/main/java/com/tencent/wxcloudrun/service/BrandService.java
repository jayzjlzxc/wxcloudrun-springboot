package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Brand;

import java.util.List;


//@Component
public interface BrandService {

    List<Brand> getAll();
    void addBrand(Brand brand);

    void deleteBrand(Integer id);
}
