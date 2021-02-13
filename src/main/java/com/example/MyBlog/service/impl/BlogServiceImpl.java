package com.example.MyBlog.service.impl;

import com.example.MyBlog.dao.*;
import com.example.MyBlog.entity.Blog;
import com.example.MyBlog.entity.BlogCategory;
import com.example.MyBlog.entity.BlogTag;
import com.example.MyBlog.entity.BlogTagRelation;
import com.example.MyBlog.service.BlogService;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogCategoryMapper blogCategoryMapper;
    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;
    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Override
    public String saveBlog(Blog blog) {
        BlogCategory blogCategory=blogCategoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if(blogCategory==null){
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        }else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank()+1);
        }
        //处理标签数据
        String[] tags=blog.getBlogTags().split(",");
        if(tags.length>6){
            return "标签数量限制为6";
        }
        //保存文章
        if(blogMapper.insertSelective(blog)>0){
            //新增的tag对象
            List<BlogTag> tagList=new ArrayList<>();
            //所有的tag对象，用于建立关系数据
            List<BlogTag> alltagList=new ArrayList<>();
            for(int i=0;i<tags.length;++i){
                BlogTag tag=blogTagMapper.selectByTagName(tags[i]);
                if(tag==null){
                    BlogTag blogTag=new BlogTag();
                    blogTag.setTagName(tags[i]);
                    tagList.add(blogTag);
                }else{
                    alltagList.add(tag);
                }
            }
            //新增标签数据并修改分类排序值
            if (!CollectionUtils.isEmpty(tagList)) {
                blogTagMapper.batchInsertBlogTag(tagList);
            }
            blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
            List<BlogTagRelation> blogTagRelations = new ArrayList<>();
            //新增关系数据
            alltagList.addAll(tagList);
            for (BlogTag tag : alltagList) {
                BlogTagRelation blogTagRelation = new BlogTagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "success";
            }
        }
        return "保存失败";
    }

    @Override
    public PageResult getBlogPage(PageQueryUtil pageQueryUtil) {
        List<Blog> blogList=blogMapper.findBlogList(pageQueryUtil);
        int total=blogMapper.getTotalBlogs(pageQueryUtil);
        PageResult pageResult=new PageResult(blogList,total,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogMapper.deleteBatch(ids)>0;
    }

    @Override
    public int getTotalBlogs() {
        return blogTagMapper.getTotalTags(null);
    }

    @Override
    public Blog getBlogById(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public String updateBlog(Blog blog) {
        Blog blogForUpdate = blogMapper.selectByPrimaryKey(blog.getBlogId());
        if(blogForUpdate == null){
            return "数据不存在";
        }
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
        blogForUpdate.setBlogContent(blog.getBlogContent());
        blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
        blogForUpdate.setBlogStatus(blog.getBlogStatus());
        blogForUpdate.setEnableComment(blog.getEnableComment());
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if(blogCategory == null){
            blogForUpdate.setBlogCategoryId(0);
            blogForUpdate.setBlogCategoryName("默认分类");
        }else{
            //设置博客分类名称
            blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
            blogForUpdate.setBlogCategoryId(blogCategory.getCategoryId());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        blogForUpdate.setBlogTags(blog.getBlogTags());
        //新增的tag对象
        List<BlogTag> tagListForInsert = new ArrayList<>();
        //所有的tag对象，用于建立关系数据
        List<BlogTag> allTagsList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BlogTag tag = blogTagMapper.selectByTagName(tags[i]);
            if (tag == null) {
                //不存在就新增
                BlogTag tempTag = new BlogTag();
                tempTag.setTagName(tags[i]);
                tagListForInsert.add(tempTag);
            } else {
                allTagsList.add(tag);
            }
        }
        //新增标签数据不为空->新增标签数据
        if (!CollectionUtils.isEmpty(tagListForInsert)) {
            blogTagMapper.batchInsertBlogTag(tagListForInsert);
        }
        List<BlogTagRelation> blogTagRelations = new ArrayList<>();
        //新增关系数据
        allTagsList.addAll(tagListForInsert);
        for (BlogTag tag : allTagsList) {
            BlogTagRelation blogTagRelation = new BlogTagRelation();
            blogTagRelation.setBlogId(blog.getBlogId());
            blogTagRelation.setTagId(tag.getTagId());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
        blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
        blogTagRelationMapper.deleteByBlogId(blog.getBlogId());
        blogTagRelationMapper.batchInsert(blogTagRelations);
        if (blogMapper.updateByPrimaryKeySelective(blogForUpdate) > 0) {
            return "success";
        }
        return "修改失败";
    }

    @Override
    public PageResult getBlogsForIndexPage(int page) {
        Map params = new HashMap();
        params.put("page",page);
        params.put("limit",8);
        params.put("blogStatus",1);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
        return null;
    }

    @Override
    public PageResult getBlogsPageByTag(String tagName, int age) {
        return null;
    }

    @Override
    public PageResult getBlogsPageByCategory(String categoryId, int page) {
        return null;
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, int page) {
        return null;
    }
}
