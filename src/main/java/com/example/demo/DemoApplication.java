package com.example.demo;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.mapper")
@EnableScheduling
public class DemoApplication {

    /*@Bean
    public CommandLineRunner setupDefaultUser(UserService userService) {
        return args -> {
            userService.save(new User("user", "user" ,"password", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        };
    }*/

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
