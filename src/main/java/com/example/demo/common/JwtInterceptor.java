package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals(HEADER_AUTH)){
                if(c.getValue() != null && jwtService.isUsable(c.getValue())){
                    c.setMaxAge(60*60*1);
                    response.addCookie(c);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

}
