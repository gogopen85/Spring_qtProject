package com.example.demo.mapper;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProjectMapper {
    List<Integer> getData(Map map);
    List<Integer> getMarkings(Map map);
    List<Map> getMarkingsInfo();
    int insertMarkings(Map map);
    int getDataInfoCount(String userId);
    int deleteMarkings(Map map);
    int confirmData(Map map);
    int getDataId();
    int insertData(Map map);


}
