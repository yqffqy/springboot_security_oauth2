package com.fw.utils;


import com.alibaba.fastjson.JSONObject;
import com.fw.config.entity.RequestTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class Resource {
    /**
     * 导入资源实例
     */
    @Autowired
    private RequestTypeEntity resourceEntity;

    public ResponseEntity getResponse(String token){
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头
        HttpHeaders headers  = resourceEntity.getHeaders();
        headers.add("Authorization","Bearer "+token);
        HttpEntity entity = new HttpEntity(null,headers);
        try {
            return restTemplate.exchange(resourceEntity.getUrl(), HttpMethod.GET,entity,JSONObject.class);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity getResponse(String token,String type,String url) {
        return null;
    }
}

