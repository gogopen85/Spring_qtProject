package com.example.demo.mapper;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper {
    List<Integer> getData(long id);
    List<Integer> getMarkings(long id);
    int insertMarkings(int point);
}
