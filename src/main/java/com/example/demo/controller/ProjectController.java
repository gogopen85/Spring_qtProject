package com.example.demo.controller;

import com.example.demo.common.CommonResponseEntity;
import com.example.demo.entities.Markings;
import com.example.demo.pojos.UserRegistration;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/project/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    CommonResponseEntity res = new CommonResponseEntity();

    @GetMapping(value = "getData/{id}")
    public ResponseEntity<?> getData(@PathVariable long id){
        return res.resSuccess(projectService.getData(id));
    }

    @GetMapping(value = "getMarkings/{id}")
    public ResponseEntity<?> getMarkings(@PathVariable long id){
        return res.resSuccess(projectService.getMarking(id));
    }

    @PostMapping(value="insertMarkings")
    public ResponseEntity<?> insertMarkings(@RequestBody Map map, HttpServletResponse response){
        projectService.insertMarkings(Integer.parseInt(map.get("addMarkings").toString()));
        return res.resSuccess("success");
    }

}
