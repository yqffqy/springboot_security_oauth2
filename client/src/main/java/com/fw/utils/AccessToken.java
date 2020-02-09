package com.fw.utils;

import com.alibaba.fastjson.JSONObject;
import com.fw.config.entity.ClientEntity;
import com.fw.config.entity.RequestTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AccessToken {
    /**
     * 导入认证服务器实例
     * */
    @Autowired
    private RequestTypeEntity authEntity;
    /**
     * 设置编码格式
     * */
    @Autowired
    private Encode encode;
    /**
     * 导入客户端信息
     * */
    @Autowired
    ClientEntity clientEntity;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity getResponse(String code){
        //设置请求体
        MultiValueMap<String,Object> requestBody = authEntity.getBody();
        requestBody.set("code",code);
        //设置请求头
        HttpHeaders headers = authEntity.getHeaders();
        //设置编码
        String clientAndSecret = encode.getBase64ClientMsg(clientEntity.getClientId()+":"+clientEntity.getClientSecret());
        headers.add("Authorization","Basic "+clientAndSecret);
        HttpEntity<MultiValueMap<String,Object>> entity = new HttpEntity<>(requestBody,headers);
        try {
            return restTemplate.postForEntity(authEntity.getUrl(), entity, JSONObject.class);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
