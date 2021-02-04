package com.example.MyBlog.dao;

import com.example.MyBlog.entity.BlogLink;
import com.example.MyBlog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogLinkMapper {

    int deleteByPrimaryKey(Integer linkId);

    int insert(BlogLink record);

    int insertSelective(BlogLink record);

    BlogLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(BlogLink record);

    int updateByPrimaryKey(BlogLink record);

    List<BlogLink> findLinkList(PageQueryUtil pageQueryUtil);

    int getTotalLinks(PageQueryUtil pageQueryUtil);

    int deleteBatch(Integer[] ids);
}
