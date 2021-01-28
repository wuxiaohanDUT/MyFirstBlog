package com.example.MyBlog.service.impl;


import com.example.MyBlog.dao.AdminUserMapper;
import com.example.MyBlog.entity.AdminUser;
import com.example.MyBlog.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;


    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        return null;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        return null;
    }


    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName,password);
    }


}
