package com.example.MyBlog;

import com.example.MyBlog.entity.AdminUser;
import com.example.MyBlog.service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyBlogApplicationTests {

    @Resource
    AdminUserService adminUserService;
    @Test
    void contextLoads() {
        AdminUser user=adminUserService.login("wxh","e10adc3949ba59abbe56e057f20f883e");
        //adminUserService.updateByPrimaryKey(user);
        System.out.println(user);
    }

}
