package com.sankha.twitter.exception;

public class TweetNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    Long fieldValue;
    public TweetNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
