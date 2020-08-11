package com.hqyj.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId);
    PageInfo<City> getCitiesBySearchvo(int countryId, SearchVo searchVo);
}
