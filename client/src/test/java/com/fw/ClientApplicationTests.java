package com.fw;



import com.fw.config.entity.ClientEntity;
import com.fw.config.entity.RequestTypeEntity;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;

@SpringBootTest
class ClientApplicationTests {

    @Autowired
    RequestTypeEntity authEntity;

    @Autowired
    RequestTypeEntity resourceEntity;

    @Autowired
    ClientEntity clientEntity;
    @Test
    void contextLoads() throws IOException {
        System.out.println(clientEntity);
    }

    @Test
    public void testOauth(){
        RestTemplate restTemplate = new RestTemplate();
        String user = "client";
        String password = "123";
        String userMsg = user + ":" + password;
        String base64UserMsg = Base64.getEncoder().encodeToString(userMsg.getBytes());
        System.out.println();
        System.out.println(base64UserMsg);
        String url = "http://localhost:8080/oauth/token";
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("grant_type","authorization_code");
        requestEntity.add("code","CEL2AR");
        requestEntity.add("redirect_uri","http://127.0.0.1:8082/index");
        requestEntity.add("scope","all");
        System.out.println(requestEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Basic "+base64UserMsg);
        System.out.println(headers);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(requestEntity, headers);
        System.out.println(entity);
        try {
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, entity, JSONObject.class);
            System.out.println(response);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testEntity(){

     System.out.println(resourceEntity);
    }

}
