package com.fw.config.bean;

import com.fw.config.entity.ClientEntity;
import com.fw.config.entity.RequestTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class RequestConfig {

    /**
     * 认证服务器配置方式
     * */
    @Bean
    @ConfigurationProperties(prefix = "auth.first")
    public RequestTypeEntity authEntity(){
        return new RequestTypeEntity();
    }
    /**
     * 资源服务器配置
     * */
    @Bean
    @ConfigurationProperties(prefix = "resource.first")
    public RequestTypeEntity resourceEntity(){
        return new RequestTypeEntity();
    }


}
