package com.example.demo.controller;

import com.example.demo.common.CommonResponseEntity;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.pojos.UserRegistration;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String root(){ return "/main"; }

    @RequestMapping("/main")
    public String main(){
        return "/main";
    }

    @RequestMapping("/signUp")
    public String register(){ return "/user/signUp"; }

    @RequestMapping("/login")
    public String login(){ return "/user/login"; }

    @RequestMapping("/board")
    public String board(){
        return "/board/board";
    }

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "/board/dashboard";
    }

    @RequestMapping("/project/myProject")
    public String myProject(){
        return "/project/myProject";
    }
}
