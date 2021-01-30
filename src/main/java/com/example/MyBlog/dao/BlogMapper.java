package com.example.MyBlog.dao;

import com.example.MyBlog.entity.Blog;
import com.example.MyBlog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

    /**
     * 根据BlogID删除一条博客
     * @param blogId
     * @return
     */
    int deleteByPrimaryKey(Long blogId);

    /**
     * 插入一条博客
     * @param record
     * @return
     */
    int insert(Blog record);

    /**
     * 选择性地插入博客信息
     * @param record
     * @return
     */
    int insertSelective(Blog record);

    /**
     * 根据BlogID查询博客
     * @param blogId
     * @return
     */
    Blog selectByPrimaryKey(Long blogId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 查询博客，并进行分页，添加一些额外的约束条件
     * @param pageUtil
     * @return
     */
    List<Blog> findBlogList(PageQueryUtil pageUtil);

    /**
     * 根据类型查询博客
     * @param type
     * @param limit
     * @return
     */
    List<Blog> findBlogListByType(@Param("type") int type,@Param("limit") int limit);

    /**
     * 查询某种类型的所有博客数量
     * @param pageUtil
     * @return
     */
    int getTotalBlogs(PageQueryUtil pageUtil);

    /**
     * 删除数组中ID的博客
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    /**
     * 根据TagID查询博客
     * @param pageUtil
     * @return
     */
    List<Blog> getBlogsPageByTagId(PageQueryUtil pageUtil);

    int getTotalBlogsByTagId(PageQueryUtil pageUtil);

    /**
     * 根据URL查询博客
     * @param subUrl
     * @return
     */
    Blog selectBySubUrl(String subUrl);

    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);
}
