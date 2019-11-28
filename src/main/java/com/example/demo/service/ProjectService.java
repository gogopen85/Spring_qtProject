package com.example.demo.service;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import com.example.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<int []> getData(long id){
        List<Integer> list = projectMapper.getData(id);
        List<int []> list_ = new ArrayList<>();
        for(int i = 0 ; i<list.size(); i++ ){
            int temp[] = {i, list.get(i)};
            list_.add(i, temp);
        }
        return list_;
    }

    public List<Integer> getMarking(long id){
        List<Integer> list = projectMapper.getMarkings(id);
        return list;
    }

    public int insertMarkings(int point){
        return projectMapper.insertMarkings(point);
    }



}
