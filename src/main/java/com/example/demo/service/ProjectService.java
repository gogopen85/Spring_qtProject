package com.example.demo.service;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import com.example.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Map getData(Map map){
        int dataInfoCnt = projectMapper.getDataCount(Integer.parseInt(map.get("userId").toString()));
        map.put("dataId",dataInfoCnt+1);
        List<Integer> list = projectMapper.getData(map);
        List<int []> list_ = new ArrayList<>();
        for(int i = 0 ; i<list.size(); i++ ){
            int temp[] = {i, list.get(i)};
            list_.add(i, temp);
        }
        map.put("data", list_);
        map.put("point", projectMapper.getMarkings(map));

        return map;
    }

    public List<Integer> getMarking(Map map){
        List<Integer> list = projectMapper.getMarkings(map);
        return list;
    }

    public int insertMarkings(Map map){

        int dataInfoCnt = projectMapper.getDataCount(Integer.parseInt(map.get("userId").toString()));
        map.put("dataId",dataInfoCnt+1);
        map.put("pointId",0);
        return projectMapper.insertMarkings(map);
    }



}
