package com.example.MyBlog.service;

import com.example.MyBlog.entity.BlogTagCount;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;

import java.util.List;

public interface TagService {

    /**
     * 查询标签的分页数据
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
