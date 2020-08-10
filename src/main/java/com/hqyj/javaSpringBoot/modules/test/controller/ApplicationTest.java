package com.hqyj.javaSpringBoot.modules.test.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
* @component就是说把这个类交给Spring管理
* @PropertySource:读取配置文件
* @ConfigurationProperties:设置配置类属性，设置前缀
* */
@Component
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.qq")
public class ApplicationTest {
    private String name;
    private int age;
    private String desc;
    private String random;

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

}
