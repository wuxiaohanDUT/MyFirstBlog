package com.example.MyBlog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BlogConfig {

    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;
}
