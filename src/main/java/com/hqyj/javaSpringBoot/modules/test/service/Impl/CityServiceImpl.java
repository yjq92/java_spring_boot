package com.hqyj.javaSpringBoot.modules.test.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.aspect.ServiceAnnotation;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.dao.CityDao;
import com.hqyj.javaSpringBoot.modules.test.entity.City;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    @ServiceAnnotation(value = "qqqq")
    public List<City> getCitiesByCountryId(int countryId) {
//        return cityService.getCitiesByCountryId(countryId);避免查到为空值
        return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchvo(int countryId, SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchvo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStaus.SUCCESS.status, "Insert success.", city);
    }

    /**
     * 加了Transactional事务会回滚，加上noRollbackFor，在发生异常时，事务不会回滚，依然可以提交事务
     */
    @Override
    @Transactional(noRollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        //int i = 1 / 0; //runtimeexception
        return new Result<>(Result.ResultStaus.SUCCESS.status,
                "update success.", city);
    }


    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStaus.SUCCESS.status, "delete success.");
    }
}
