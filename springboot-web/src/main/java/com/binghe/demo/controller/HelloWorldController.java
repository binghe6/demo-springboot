package com.binghe.demo.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController {
	
//	@Autowired
//	private Hello hello;
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    
    /**
     * 获取sessionId（用于测试session的redis管理是否成功，如果返回的sessionId与redis中的session的key中sessionId一致说明成功）
     * @param session
     * @return
     */
//    @GetMapping("getSessionId")
//    String uid(HttpSession session) {
//        session.setAttribute("session", UUID.randomUUID());
//        return session.getId();
//    }
    
//    @GetMapping("/test")
//    public void test() {
//    	hello.sayHello("123");
//		log.info(">>>>>>>>>>>> 1");
//		log.info(">>>>>>>>>>>> 2");
//		log.info(">>>>>>>>>>>> 3");
//		log.info(">>>>>>>>>>>> 4");
//    }
}