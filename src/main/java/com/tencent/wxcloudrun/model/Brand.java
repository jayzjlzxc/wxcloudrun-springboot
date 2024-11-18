package com.tencent.wxcloudrun.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 手机品牌，小米华为苹果等等
 */
@Data
@Builder
public class Brand {
    private Integer id;

    private String name;

    //图标,前端写死，如果前端写死的话不利于扩展，万一后面自己想扩展还得修改前端代码
    private String icon;

    //报价url
    private String priceUrl;

}
