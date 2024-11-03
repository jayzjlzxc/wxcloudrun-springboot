package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;

    private String name;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
