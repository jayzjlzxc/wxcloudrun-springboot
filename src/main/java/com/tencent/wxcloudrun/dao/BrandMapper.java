package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BrandMapper {
    List<Brand> getAllBrand();
    void addBrand(Brand b);

    void deleteBrand(Integer id);

    void updatePic(Brand brand);
}
