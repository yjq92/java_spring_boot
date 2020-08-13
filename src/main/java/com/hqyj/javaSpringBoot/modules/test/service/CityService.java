package com.hqyj.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchvo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesBySearchvo(SearchVo searchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int cityId);
}
