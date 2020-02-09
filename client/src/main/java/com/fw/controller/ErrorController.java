package com.fw.controller;

import com.fw.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("error")
public class ErrorController {
    @Autowired
    private Result result;

    @GetMapping(value = "/400")
    public Result error400(@RequestBody Object o){
        result.setMessage("");
        result.setCode(400);
        return result;
    }
}
