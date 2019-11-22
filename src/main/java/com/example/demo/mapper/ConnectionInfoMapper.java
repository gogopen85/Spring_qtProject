package com.example.demo.mapper;

import com.example.demo.entities.ConnectionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface ConnectionInfoMapper {
    List<ConnectionInfo> getConnectionInfos();
    List<ConnectionInfo> getConnectionInfo(String name);
}
