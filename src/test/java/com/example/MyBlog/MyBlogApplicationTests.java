package com.example.MyBlog;

import com.example.MyBlog.dao.BlogMapper;
import com.example.MyBlog.dao.BlogTagMapper;
import com.example.MyBlog.entity.AdminUser;
import com.example.MyBlog.entity.Blog;
import com.example.MyBlog.entity.BlogTag;
import com.example.MyBlog.service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyBlogApplicationTests {

    @Resource
    AdminUserService adminUserService;

    @Resource
    BlogMapper blogMapper;

    @Resource
    BlogTagMapper blogTagMapper;
    @Test
    void contextLoads() {
        System.out.println(blogTagMapper.selectByTagName("Spring"));
    }

}
