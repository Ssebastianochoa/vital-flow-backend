package com.vital_flow.demo.model.exceptions;

public class BusinessException extends Exception {

    public BusinessException(String message){
        super("Business rule exception: " + message);
    }
}
