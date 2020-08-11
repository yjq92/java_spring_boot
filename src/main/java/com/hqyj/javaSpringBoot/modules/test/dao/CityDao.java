package com.hqyj.javaSpringBoot.modules.test.dao;

import com.hqyj.javaSpringBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {
    @Select("select * from m_city where country_id=#{countryId}")
    List<City> getCitiesByCountryId(int countryId);
}
