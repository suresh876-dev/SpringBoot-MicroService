package com.Suresh.SpringBoot_MicroService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Long filedValue;


    public ResourceNotFoundException(String resourceName,String fieldName,Long filedValue)
    {
        super(String.format("%s not found with %s: '%s'",resourceName,fieldName,filedValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.filedValue=filedValue;

    }
}
