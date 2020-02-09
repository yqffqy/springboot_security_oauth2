package com.fw.controller;

import io.netty.util.concurrent.DefaultPromise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller

@SessionAttributes("authorizationRequest")
public class OauthController {
    private String loginProcessingUrl = "/authorization/form";
    /**
     * 登录页面
     * */
    @RequestMapping("/oauth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl",loginProcessingUrl);
        return "login";
    }

    /**
     * 授权页面
     * */
    @RequestMapping("/oauth/grant")
    public ModelAndView grantPage(Map<String, Object> model, HttpServletRequest request){
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("grant");
        // 传递 scope 过去,Set 集合
        view.addObject("scopes", authorizationRequest.getScope());
        // 拼接一下名字
        view.addObject("scopeName", String.join(",", authorizationRequest.getScope()));
        view.addObject("clientId", authorizationRequest.getClientId());
        return view;
    }
}
