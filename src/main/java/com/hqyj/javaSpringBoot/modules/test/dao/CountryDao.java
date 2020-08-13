package com.hqyj.javaSpringBoot.modules.test.dao;

import com.hqyj.javaSpringBoot.modules.test.entity.Country;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 本身的country_id已经被占用，再使用一个Result将country_id再映射给本身，另外一个映射给cities属性
 */
@Repository
@Mapper
public interface CountryDao {
    /*组合查询，获取Country以及其下面的城市信息*/
    @Select("select * from m_country where country_Id=#{countryId}")
    @Results(id="countryResults",value = {
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "country_id", property = "cities",
                    javaType = List.class,
                    many = @Many(select =
                            "com.hqyj.javaSpringBoot.modules.test.dao.CityDao.getCitiesByCountryId"))
    })
    Country getCountryByCountryId(int countryId);
    @Select("select * from m_country where country_name=#{countryName}")
    @ResultMap(value = "countryResults")
    Country getCountryByCountryName(String countryName);

}
