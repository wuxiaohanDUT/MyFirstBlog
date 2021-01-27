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
    public int insert(AdminUser record) {
        return adminUserMapper.insert(record);
    }

    @Override
    public int insertSelective(AdminUser record) {
        return adminUserMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdminUser record) {
        return adminUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName,password);
    }


}
