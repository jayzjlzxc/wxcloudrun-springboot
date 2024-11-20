package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tencent.wxcloudrun.controller.BrandController;
import com.tencent.wxcloudrun.dao.BrandMapper;
import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.service.BrandService;
import com.tencent.wxcloudrun.util.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper mapper;

    final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);;

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
    public Boolean update(MultipartFile file, Integer id) {
        try {
            byte[] bytes = file.getBytes();
            String picUrl = FileUpload.upload(id + ".png", bytes);
            Brand brand = Brand.builder().id(id).priceUrl(picUrl).build();
            mapper.updatePic(brand);
            return true;
        } catch (IOException e) {
            logger.error("更新报价失败，原因为：", e);
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public List<Brand> getAllPrice() {
        return mapper.getAllPrice();
    }
}
