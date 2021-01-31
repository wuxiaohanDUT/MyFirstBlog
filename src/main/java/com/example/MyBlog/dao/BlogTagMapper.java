package com.example.MyBlog.dao;


import com.example.MyBlog.entity.BlogTag;
import com.example.MyBlog.entity.BlogTagCount;
import com.example.MyBlog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogTagMapper {

    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer tagId);

    BlogTag selectByTagName(String tagName);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> findTagList(PageQueryUtil pageQueryUtil);

    List<BlogTagCount> getTagCount();

    int getTotalTags(PageQueryUtil pageQueryUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTag> tagList);
}
