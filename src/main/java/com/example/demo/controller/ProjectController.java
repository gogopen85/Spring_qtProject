package com.example.demo.controller;

import com.example.demo.common.CommonResponseEntity;
import com.example.demo.common.UnauthorizedException;
import com.example.demo.entities.Markings;
import com.example.demo.pojos.UserRegistration;
import com.example.demo.service.ProjectService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    CommonResponseEntity res = new CommonResponseEntity();

    @GetMapping(value = "getData/{userId}/{pageNo}")
    public ResponseEntity<?> getData(@PathVariable String userId, @PathVariable String pageNo,HttpServletRequest request){
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("pageNo", pageNo);
        return res.resSuccess(projectService.getData(map));
    }

    @GetMapping(value = "getMarkedData/{userId}/{pageNo}/{projectNo}")
    public ResponseEntity<?> getData(@PathVariable String userId, @PathVariable String pageNo,@PathVariable String projectNo,HttpServletRequest request){
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("pageNo", pageNo);
        map.put("projectNo",projectNo);
        return res.resSuccess(projectService.getData(map));
    }

    @GetMapping(value = "getMarkings/{dataId}/{userId}")
    public ResponseEntity<?> getMarkings(@PathVariable long dataId, @PathVariable int userId){
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("dataId",dataId);
        return res.resSuccess(projectService.getMarking(map));
    }

    @PostMapping(value="insertMarkings")
    public ResponseEntity<?> insertMarkings(@RequestBody Map map){
        projectService.insertMarkings(map);
        return res.resSuccess("success");
    }

    @PostMapping(value="deleteMarkings")
    public ResponseEntity<?> deleteMarkings(@RequestBody Map map){
        projectService.deleteMarkings(map);
        return res.resSuccess("success");
    }

    @PostMapping(value="confirmData")
    public ResponseEntity<?> confirmData(@RequestBody Map map){
        projectService.confirmData(map);
        return res.resSuccess("success");
    }

}
