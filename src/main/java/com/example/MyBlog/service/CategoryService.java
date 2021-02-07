package com.example.MyBlog.service;


import com.example.MyBlog.entity.BlogCategory;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     *查询分页的分页数据
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分页数据
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<BlogCategory> getAllCategories();
}
