package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.BrandMapper;
import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.BrandService;
import com.tencent.wxcloudrun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
