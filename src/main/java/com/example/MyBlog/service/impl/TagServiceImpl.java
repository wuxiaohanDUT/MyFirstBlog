package com.example.MyBlog.service.impl;

import com.example.MyBlog.dao.BlogMapper;
import com.example.MyBlog.dao.BlogTagMapper;
import com.example.MyBlog.dao.BlogTagRelationMapper;
import com.example.MyBlog.entity.BlogTag;
import com.example.MyBlog.entity.BlogTagCount;
import com.example.MyBlog.service.TagService;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class TagServiceImpl implements TagService {

    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;


    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTag> tags = blogTagMapper.findTagList(pageUtil);
        int total = blogTagMapper.getTotalTags(pageUtil);
        PageResult pageResult=new PageResult(tags,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalTags() {
        return blogTagMapper.getTotalTags(null);
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTag blogTag=blogTagMapper.selectByTagName(tagName);
        if(blogTag==null){
            BlogTag blogTag1=new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag1)>0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        //已存在关联关系不删除
        List<Long> relations = blogTagRelationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return blogTagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }
}
