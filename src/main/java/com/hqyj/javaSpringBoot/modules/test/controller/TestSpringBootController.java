package com.hqyj.javaSpringBoot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestSpringBootController {

    /**
     * 127.0.0.1:8080/test/testOne
     * 测试框架搭建是否成功
     */
    @GetMapping("/testOne")
    @ResponseBody
    public String testOne(){
        return "Test SpringBoot Successfully!";
    }

}
