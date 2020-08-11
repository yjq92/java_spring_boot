package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestSpringBootController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestSpringBootController.class);

    @GetMapping("/logTest")
    @ResponseBody
    public String logTest(){
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test111111";
    }

    //测试log 日志

    /**
     * 127.0.0.1:8080/test/testOne
     * 测试框架搭建是否成功
     */
    @GetMapping("/testOne")
    @ResponseBody
    public String testOne(){
        return "Test SpringBoot Successfully!";
    }

    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    //引入ApplicationTest组件
    @Autowired
    private ApplicationTest applicationTest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }


    /**
     * 127.0.0.1:8080/test/configTest
     */
    @GetMapping("/configTest")
    @ResponseBody
    public String configTest(){
        return new StringBuffer().append(name).append("-----")
                .append(age).append("--------")
                .append(desc).append("------")
                .append(random).append("------").toString();
    }
    /**
     * 127.0.0.1:8080/test/configTest2
     */
    @GetMapping("/configTest2")
    @ResponseBody
    public String configTest2(){
        StringBuffer s1=new StringBuffer();
        return s1.append(applicationTest.getName()).append("-----")
                .append(applicationTest.getAge()).append("--------")
                .append(applicationTest.getDesc()).append("------")
                .append(applicationTest.getRandom()).append("------").toString();
    }

}
