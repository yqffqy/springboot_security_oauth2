package com.fw.controller;

import com.fw.domain.Result;
import com.fw.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("resource")
public class ResourceController {
    @GetMapping
    public User resource(){

        return new User();
    }
}
