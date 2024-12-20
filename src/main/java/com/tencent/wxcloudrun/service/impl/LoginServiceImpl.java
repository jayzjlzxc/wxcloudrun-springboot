package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper mapper;
    @Override
    public Boolean login(UserInfo userInfo) {
        if ("changjiang".equalsIgnoreCase(userInfo.getUsername())&&"changjiang666".equalsIgnoreCase(userInfo.getPassword())){
            return true;
        }else{
            return false;
        }
//        User user = new User();
//        user.setName(userInfo.getUsername());
//        user.setPassword(userInfo.getPassword());
//        mapper.addUser(user);
    }
}
