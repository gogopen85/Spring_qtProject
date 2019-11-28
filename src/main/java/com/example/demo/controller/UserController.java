package com.example.demo.controller;

import com.example.demo.common.CommonResponseEntity;
import com.example.demo.common.JwtService;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.pojos.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    CommonResponseEntity res = new CommonResponseEntity();

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegistration userRegistration){
        if("".equals(userRegistration.getName()))
            return res.resBadRequest("이름은 공백이 될 수 없습니다.");
        else if("".equals(userRegistration.getUsername()))
            return res.resBadRequest("아이디는 공백이 될 수 없습니다.");
        else if(userService.getUser(userRegistration.getUsername()).equals(Optional.empty()) == false)
            return res.resBadRequest("동일한 아이디가 등록되어 있습니다.");
        else if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation()))
            return res.resBadRequest("비밀번호가 일치하지 않습니다.");

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(userRegistration.getUsername()).find())
            return res.resBadRequest("아이디에는 특수문자가 포함될 수 없습니다.");

        userService.save(new User(userRegistration.getUsername(), userRegistration.getName(), new Date(), userRegistration.getPassword(), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));

        return res.resSuccess("가입완료");
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody UserRegistration user, HttpServletResponse response){
        if(userService.checkUserPassword(user) == true){
            String token = jwtService.create("login",user,"login");
            Cookie cookie = new Cookie("Authorization",token);
            cookie.setMaxAge(60*60*1);
            cookie.setPath("/");
            response.addCookie(cookie);
            return res.resSuccess(token);
        }
        return res.resBadRequest("로그인 정보를 확인해주세요");
    }

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }
}
