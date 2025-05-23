package com.aloha.security5.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    
    /**
     * 사용자 페이지
     * @return
     */
    @GetMapping({"/", ""})
    public String index() {
        log.info("/user");
        return "/user/index";
    }
    

}
