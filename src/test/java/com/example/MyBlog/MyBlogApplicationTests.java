package com.example.MyBlog;

import com.example.MyBlog.dao.BlogMapper;
import com.example.MyBlog.entity.AdminUser;
import com.example.MyBlog.entity.Blog;
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

    @Resource
    BlogMapper blogMapper;
    @Test
    void contextLoads() {
        Blog blog=new Blog();
        blog.setBlogId((long)7);
        blogMapper.insertSelective(blog);
    }

}
