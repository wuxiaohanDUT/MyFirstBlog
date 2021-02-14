package com.example.MyBlog.service;

import com.example.MyBlog.controller.vo.BlogDetailVO;
import com.example.MyBlog.controller.vo.SimpleBlogListVO;
import com.example.MyBlog.entity.Blog;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;

import java.util.List;
import java.util.SimpleTimeZone;

public interface BlogService {

    String saveBlog(Blog blog);

    PageResult getBlogPage(PageQueryUtil pageQueryUtil);

    Boolean deleteBatch(Integer[] ids);

    int getTotalBlogs();

    Blog getBlogById(Long blogId);

    String updateBlog(Blog blog);

    PageResult getBlogsForIndexPage(int page);

    List<SimpleBlogListVO> getBlogListForIndexPage(int type);

    BlogDetailVO getBlogDetail(Long blogId);

    PageResult getBlogsPageByTag(String tagName,int age);

    PageResult getBlogsPageByCategory(String categoryId,int page);

    PageResult getBlogsPageBySearch(String keyword,int page);

    BlogDetailVO getBlogDetailBySubUrl(String subUrl);
}
