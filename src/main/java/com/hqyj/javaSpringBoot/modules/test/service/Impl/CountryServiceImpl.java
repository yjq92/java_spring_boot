package com.hqyj.javaSpringBoot.modules.test.service.Impl;

import com.hqyj.javaSpringBoot.modules.test.dao.CountryDao;
import com.hqyj.javaSpringBoot.modules.test.entity.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import com.hqyj.javaSpringBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country migrateCountryByRedis(int countryId) {
        Country country=countryDao.getCountryByCountryId(countryId);
        String countryKey=String.format("country%d",countryId);
        System.out.println("----------------->"+countryKey); //country522
        redisUtils.set(countryKey,country);
        return (Country) redisUtils.get(countryKey);
    }


}
