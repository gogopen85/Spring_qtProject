package com.example.demo.mapper;

import com.example.demo.entities.User;
import com.example.demo.pojos.UserRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    int saveUser(UserRegistration UserRegistration);
    User getUser(String username);
    User updateUser(UserRegistration UserRegistration);


    String checkUserPassword(UserRegistration user);


}
