package com.hqyj.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.User;
import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 127.0.0.1/api/user ----put
     * {"userName":"lisa6","userImg":"/1.jpg","userId":"9"}
     */
    @PutMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user){
       return userService.updateUser(user);
    }

    /**
     * 127.0.0.1/api/user/11-----get
     */
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    /**
     *127.0.0.1/api/user/1-----get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){
        return userService.getUserByUserId(userId);

    }

    /**
     * 127.0.0.1/api/userImg ---- post
     */
    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file) {
        return userService.uploadUserImg(file);
    }

    /**
     * 127.0.0.1/api/profile ---- put
     */
    @PutMapping(value = "/profile", consumes = "application/json")
    public Result<User> updateUserProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }
}
