package com.fw.controller;

import com.alibaba.fastjson.JSONObject;
import com.fw.domain.Result;
import com.fw.utils.AccessToken;
import com.fw.utils.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("accessToken")
public class AccessTokenController {

    @Autowired
    Result result;
    @Autowired
    private AccessToken accessToken;
    @Autowired
    Resource resource;
    @PostMapping
    public Result getToken(@RequestBody JSONObject jsonObject,HttpSession session){
        ResponseEntity responseEntity = accessToken.getResponse(jsonObject.get("code").toString());
        if(responseEntity.getStatusCodeValue()==200){
            long openId = System.currentTimeMillis();
            Object accessToken = responseEntity.getBody();
            //这里为了方便，只存openid　对应的access_token
            session.setAttribute(String.valueOf(openId),accessToken);
            //可以通过check_token 获取资源服务接口获取更多信息
            //使用时间戳生成openId，对于当用户来说是唯一值，多用户可可能同一时刻操作
            Map<String,Long> openIdObject = new HashMap<>();
            openIdObject.put("openId",openId);
            result.setCode(0);
            result.setMessage("success");
            result.setData(openIdObject);
        }else {
            result.setCode(1);
            result.setMessage(Objects.requireNonNull(responseEntity.getBody()).toString());
        }
        return result;
    }
}
