package com.fw.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;
@Component
public class Encode {
    /**
     * 设置６４编码
     * */
    public String getBase64ClientMsg(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
}
