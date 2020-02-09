package com.fw.controller;

import com.alibaba.fastjson.JSONObject;
import com.fw.domain.Result;
import com.fw.utils.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    Result result;
    @Autowired
    Resource resource;
    @PostMapping
    public Result getResource(@RequestBody JSONObject jsonObject,HttpSession session) {
        String openId = jsonObject.get("openId").toString();
        System.out.println(openId);
        Map<String,Object> accessToken = (Map<String, Object>) session.getAttribute(openId);
        if(accessToken.isEmpty()){
            result.setCode(2);
            result.setMessage("当前信息已经过期");
        }
        ResponseEntity responseEntity =  resource.getResponse(accessToken.get("access_token").toString());
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        if(responseEntity.getStatusCodeValue()==200){
            result.setCode(0);
            result.setMessage("success");
            result.setData(responseEntity.getBody());
        }else {
            result.setCode(1);
            result.setMessage(responseEntity.getBody().toString());
            result.setData(null);
        }

       return result;
    }
}
