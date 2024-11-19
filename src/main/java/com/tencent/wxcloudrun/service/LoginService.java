package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.UserInfo;
import org.springframework.stereotype.Component;


//@Component
public interface LoginService {
    Boolean login(UserInfo userInfo);
}
