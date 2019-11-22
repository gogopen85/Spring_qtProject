package com.example.demo.service.threadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class threadServiceEX {

    @Autowired
    private ApplicationContext ctx;

    private HashMap<String,String> param;

    public void setParams(HashMap<String,String> param) {
        this.param = param;
    }

    public void runThread() {
        /*
        Runnable task = () -> {
            ctx.getBean({className}.class).methodName(param);
        };
        new Thread(task).start();
        */
    }

}
