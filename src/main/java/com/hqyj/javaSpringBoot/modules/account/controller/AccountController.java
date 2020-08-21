package com.hqyj.javaSpringBoot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     * 127.0.0.1/account/users
     */
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }

    /**
     * 127.0.0.1/account/login
     */
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }
    /**
     * 127.0.0.1/account/register
     */
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }
}
