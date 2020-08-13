package com.hqyj.javaSpringBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.City;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * @param countryId
     * 127.0.0.1/api/cities/522
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId){
        return cityService.getCitiesByCountryId(countryId);
    }

    /**
     * @param countryId
     * @param searchVo
     * 127.0.0.1/api/cities/522----post
     * {"currentPage":"1","pageSize":"5"}
     */
    @PostMapping(value="/cities/{countryId}",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@PathVariable int countryId, @RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchvo(countryId,searchVo);
    }

    /**
     * @param searchVo
     * 127.0.0.1/api/cities----post
     * {"currentPage":"1","pageSize":"5","keyWord":"Si","orderBy":"city_name","sort":"asc"}
     */
    @PostMapping(value = "/cities",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchvo(searchVo);
    }

    /**
     * @param city
     * 127.0.0.1/api/inserCity---post
     * {"cityName":"ms","localCityName":"freeCity","countryId":"522"}
     */
    @PostMapping(value = "/inserCity",consumes = "application/json")
    public Result<City> inserCity(@RequestBody City city){
        return cityService.insertCity(city);
    }

    /**
     * @param city
     * 127.0.0.1/api/updateCity
     * "cityId"="2259","cityName""="aaaaa"
     */
    @PutMapping(value = "/updateCity",consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /**
     *
     * 127.0.0.1/api/deleteCity/2258
     */
    @DeleteMapping(value = "/deleteCity/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId){
        return cityService.deleteCity(cityId);
    }

}
