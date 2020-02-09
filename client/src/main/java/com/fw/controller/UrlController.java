package com.fw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller

public class UrlController {
    @RequestMapping("/login")
    @GetMapping
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/index")
    @GetMapping
    public String getIndex(){
        return "index";
    }

}
