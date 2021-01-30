package com.example.MyBlog.dao;


import com.example.MyBlog.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminUserMapper {
    /**
     * 向表中插入记录，一个用户的信息
     * @param record
     * @return
     */
    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    /**
     * 登陆方法
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据用户的ID查找用户的信息
     * @param adminUserId
     * @return
     */
    AdminUser selectByPrimaryKey(Integer adminUserId);

    /**
     * 选择性地更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 根据用户ID更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdminUser record);
}