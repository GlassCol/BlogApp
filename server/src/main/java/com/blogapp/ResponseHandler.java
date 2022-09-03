package com.blogapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/* Use for setting and formatting response object */
public class ResponseHandler {

    private ResponseHandler() {}

    public static ResponseEntity<Object> response(
            Object data, HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("code", status.value());
        map.put("message", message);
        map.put("status", status);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> response(
            Object data, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("code", status.value());
        map.put("message", "");
        map.put("status", status);
        return new ResponseEntity<>(map, status);
    }

}
