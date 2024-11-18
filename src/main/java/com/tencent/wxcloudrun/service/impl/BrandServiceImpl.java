package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.BrandMapper;
import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.BrandService;
import com.tencent.wxcloudrun.service.LoginService;
import com.tencent.wxcloudrun.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper mapper;

    @Override
    public List<Brand> getAll() {
        return mapper.getAll();
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
            String picUrl = FileUpload.uploadMain(String.valueOf(id) + ".png", bytes);
            Brand brand = Brand.builder().id(id).priceUrl(picUrl).build();
            mapper.updatePic(brand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
