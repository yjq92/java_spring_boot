package com.hqyj.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRoleDao {
    @Delete("delete from user_role where user_id=#{userId}")
    void deleteUserRoleByUserId(int userId);

    @Insert("insert user_role(role_id, user_id) value(#{roleId}, #{userId})")
    void addUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
