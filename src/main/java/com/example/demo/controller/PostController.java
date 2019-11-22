package com.example.demo.controller;

import com.example.demo.common.CommonResponseEntity;
import com.example.demo.entities.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/Post/")
public class PostController {

    @Autowired
    private PostService postService;

    CommonResponseEntity res = new CommonResponseEntity();

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody Post post){

        if("".equals(post.getTitle()))
            return res.resBadRequest("제목은 공백이 될 수 없습니다.");
        else if("".equals(post.getBody()))
            return res.resBadRequest("내용은 공백이 될 수 없습니다.");

        postService.insert(post);

        return res.resSuccess("저장완료");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPost(@PathVariable long id){
        Post post = postService.find(id);
        if(post != null){
            return res.resSuccess(post);
        } else {
            return res.resBadRequest("");
        }
    }






}
