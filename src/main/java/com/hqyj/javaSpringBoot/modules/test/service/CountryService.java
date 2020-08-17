package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.test.dao.CountryDao;
import com.hqyj.javaSpringBoot.modules.test.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;

public interface CountryService {

    Country getCountryByCountryId(int countryId);
    Country getCountryByCountryName(String countryName);
    Country migrateCountryByRedis(int countryId);

}
