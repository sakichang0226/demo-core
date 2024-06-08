package com.example.dynamodb.demo.exception;

public class DynamoDbDemoException extends RuntimeException {

    public DynamoDbDemoException(String message) {
        super(message);
    }

    public DynamoDbDemoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DynamoDbDemoException(String message, Throwable throwable, Object... objects) {
        super(message, throwable);
    }

}
