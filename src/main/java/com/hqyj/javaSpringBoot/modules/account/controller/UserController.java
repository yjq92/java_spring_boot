package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/api/user-----post
     *{"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user){
        return userService.insetUser(user);
    }

    /**
     * 127.0.0.1/api/login ---post
     * {"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 127.0.0.1/api/users ---- post (getusers)
     * {"currentPage":"1","pageSize":"5","keyWord":"li","orderBy":"user_name","sort":"ASC"}
     */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return userService.getUsersBySearchVo(searchVo);
    }
}
