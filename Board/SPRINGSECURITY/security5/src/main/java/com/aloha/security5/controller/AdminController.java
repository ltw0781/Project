package com.aloha.security5.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping({"", "/"})
    public String index() {
        log.info("/admin");
        return "/admin/index";
    }
    

}
