package com.hqyj.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRoleDao {
    @Delete("delete from user_role where user_id=#{userId}")
    void deleteUserRoleByUserId(int userId);
}
