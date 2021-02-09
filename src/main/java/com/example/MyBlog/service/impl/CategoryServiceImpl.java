package com.example.MyBlog.service.impl;

import com.example.MyBlog.dao.BlogCategoryMapper;
import com.example.MyBlog.dao.BlogCommentMapper;
import com.example.MyBlog.dao.BlogMapper;
import com.example.MyBlog.entity.BlogCategory;
import com.example.MyBlog.service.CategoryService;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;

import javax.annotation.Resource;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Resource
    private BlogCategoryMapper blogCategoryMapper;
    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<BlogCategory> categoryList=blogCategoryMapper.findCategoryList(pageUtil);
        int total=blogCategoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult=new PageResult(categoryList,total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory temp=blogCategoryMapper.selectByCategoryName(categoryName);
        if(temp==null){
            BlogCategory blogCategory=new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insert(blogCategory)>0;
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory=blogCategoryMapper.selectByPrimaryKey(categoryId);
        if(blogCategory!=null){
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            blogMapper.updateBlogCategorys(categoryName,blogCategory.getCategoryId(),new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKey(blogCategory)>0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if(ids.length<1){
            return false;
        }
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        return blogCategoryMapper.deleteBatch(ids)>0;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }
}
