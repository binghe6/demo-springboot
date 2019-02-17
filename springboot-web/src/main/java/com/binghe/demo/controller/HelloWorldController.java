package com.binghe.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    
    /**
     * 获取sessionId（用于测试session的redis管理是否成功，如果返回的sessionId与redis中的session的key中sessionId一致说明成功）
     * @param session
     * @return
     */
    @GetMapping("getSessionId")
    String uid(HttpSession session) {
        session.setAttribute("session", UUID.randomUUID());
        return session.getId();
    }
}