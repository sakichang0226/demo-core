package com.example.dynamodb.demo.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoDbDemoSystemException extends DynamoDbDemoException {

    public DynamoDbDemoSystemException(String message) {
        super(message);
        log.error(message);
    }

    public DynamoDbDemoSystemException(String message, Throwable throwable) {
        super(message, throwable);
        log.error(message);
    }

    public DynamoDbDemoSystemException(String message, Throwable throwable, Object... objects) {
        super(message, throwable);
        log.error(message, objects);
    }

}
