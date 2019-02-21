package com.binghe.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.binghe.demo.common.util.CodeUtil;
//import com.binghe.demo.common.util.RedisUtil;
//import com.binghe.demo.common.util.SpringAppHolder;

/**
 * 身份验证拦截器
 * token作为用户登陆的唯一凭证，此拦截器验签是为了防止token被篡改
 * @author binghe
 *
 */
@Slf4j
public class IdentityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("X-BingHe-Json-Api-Token");
        String signature = request.getHeader("X-BingHe-Json-Api-Signature");
        String signTimestamp = request.getHeader("X-BingHe-Json-Api-Signature-Timestamp");
        String nonce = request.getHeader("X-BingHe-Json-Api-Nonce");
        log.info(" >>>>>>>>>>> IdentityInterceptor ,uri:{}, Token:{}, Signature:{}, Timestamp:{}, Nonce:{}",request.getRequestURI(), token, signature, signTimestamp, nonce);
        if (token == null) {
             // token不存在
        }
        
        // 校验加密
        if (!StringUtils.isEmpty(signature)) {
            StringBuffer toSign = (new StringBuffer(token)).append(nonce).append(signTimestamp);
            String sign = CodeUtil.hexSHA1(toSign.toString());
            if (!sign.equals(signature)) {
                log.error("token:{}, signTimestamp:{}, nonce:{}, toSign:{}, signature:{}, sign:{}", token, signTimestamp, nonce, toSign, signature, sign);
                // 验签不通过
            }
        }
        
//        RedisUtil redisUtil = SpringAppHolder.getBean(RedisUtil.class);
//        String userId = redisUtil.get(token);
//        if (userId == null) {
//			// token失效
//		}
//        request.setAttribute("userId", userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
