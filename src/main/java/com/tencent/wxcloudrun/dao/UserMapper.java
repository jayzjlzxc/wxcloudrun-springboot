package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  User getUser(@Param("id") Integer id);

  void addUser(User user);

  void deleteUser(Integer id);

//  void clearCount(@Param("id") Integer id);
}
