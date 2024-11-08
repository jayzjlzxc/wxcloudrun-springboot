package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Brand;
import com.tencent.wxcloudrun.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> getAll();
    void addBrand(Brand b);

    void deleteBrand(Integer id);
}
