package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonResponseEntity {
    public ResponseEntity<?> resSuccess(Object obj){
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    public ResponseEntity<?> resBadRequest(Object obj){
        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }
}
