package com.example.demo.mapper;

import com.example.demo.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProjectMapper {
    List<Integer> getData(Map map);
    List<Markings> getMarkings(Map map);
    List<Map> getMarkingsInfo();
    int insertMarkings(Map map);
    int getDataInfoCount(String userId);
    int deleteMarkings(Map map);
    int confirmData(Map map);
    int getDataId();
    int insertData(Map map);
    int checkIsValid(Map map);
    int updateMarkings(Map map);
    int deleteMarkingsConfirm(Map map);
    int insertMarkingsByConfirmUser(Map map);
    Map getPath(Map map);
    int confirmDataByConfirmUser(Map map);
    List<ConfirmMarkings> getConfirmMarkings(Map map);
    int saveComment(Map map);
    List<Board> getComments(Map map);

    int updateFlag(Map map);

}
