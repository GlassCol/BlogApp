package com.blogapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/* Use for setting and formatting response object */
public class ResponseHandler {

    private static Map<String, Object> responseObject;
    private static final String MESSAGE = "message";
    private static final String CODE = "code";
    private static final String STATUS = "status";
    private static final String DATA = "data";

    private ResponseHandler() {}

    public static ResponseEntity<Object> response(Object data, HttpStatus status, String message) {
        responseObject = new HashMap<>();
        responseObject.put(DATA, data);
        responseObject.put(CODE, status.value());
        responseObject.put(MESSAGE, message);
        responseObject.put(STATUS, status);
        return new ResponseEntity<>(responseObject, status);
    }

    public static ResponseEntity<Object> response(Object data, HttpStatus status) {
        responseObject = new HashMap<>();
        responseObject.put(DATA, data);
        responseObject.put(CODE, status.value());
        responseObject.put(MESSAGE, "OK");
        responseObject.put(STATUS, status);
        return new ResponseEntity<>(responseObject, status);
    }

//    public int getCode() {
//        return (int) ResponseHandler.responseObject.get(CODE);
//    }
//
//    public Object getDataObject() {
//        return ResponseHandler.responseObject.get(DATA);
//    }
//
//    public String getStatus() {
//        return ResponseHandler.responseObject.get(STATUS).toString();
//    }
//
//    public String getMessage() {
//        return ResponseHandler.responseObject.get(MESSAGE).toString();
//    }
}
