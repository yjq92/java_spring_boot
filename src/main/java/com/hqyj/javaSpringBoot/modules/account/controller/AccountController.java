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
}
