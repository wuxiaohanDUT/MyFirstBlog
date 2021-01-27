package com.example.MyBlog.service;


import com.example.MyBlog.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserService {

    AdminUser getUserDetailById(Integer loginUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    AdminUser login(@Param("userName") String userName, @Param("password") String password);
}
