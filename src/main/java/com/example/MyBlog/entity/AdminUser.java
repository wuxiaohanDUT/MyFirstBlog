package com.example.MyBlog.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

@Data
public class AdminUser {

    private Integer adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Byte locked;
}
