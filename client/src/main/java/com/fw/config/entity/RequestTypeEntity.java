package com.fw.config.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

@Component
public class RequestTypeEntity implements Serializable {
    private String type;
    private String url;
    private HttpHeaders headers;
    private MultiValueMap<String,Object> body;

    @Override
    public String toString() {
        return "RequestTypeEntity{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", headers=" + headers +
                ", body=" + body +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public MultiValueMap<String, Object> getBody() {
        return body;
    }

    public void setBody(MultiValueMap<String, Object> body) {
        this.body = body;
    }
}
