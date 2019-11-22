package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojos.UserRegistration;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        if(!userRepository.findByUsername(user.getUsername()).isPresent()){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public boolean checkUserPassword(UserRegistration user){
        return new BCryptPasswordEncoder().matches(user.getPassword(),userMapper.checkUserPassword(user));
    }

    public Optional<User> getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
