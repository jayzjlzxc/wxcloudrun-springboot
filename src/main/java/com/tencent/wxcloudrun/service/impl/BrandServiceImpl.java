package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tencent.wxcloudrun.dao.BrandMapper;
import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.service.BrandService;
import com.tencent.wxcloudrun.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper mapper;

    @Override
    public List<Brand> getAllBrand() {
        return mapper.getAllBrand();
    }

    @Override
    public void addBrand(Brand brand) {
        mapper.addBrand(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        mapper.deleteBrand(id);
    }

    @Override
    public String update(MultipartFile file, Integer id) {
        try {
            byte[] bytes = file.getBytes();
            String picUrl = FileUpload.upload(String.valueOf(id) + ".png", bytes);
            Brand brand = Brand.builder().id(id).priceUrl(picUrl).build();
            mapper.updatePic(brand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Brand> getAllPrice() {
        return mapper.getAllPrice();
    }
}
