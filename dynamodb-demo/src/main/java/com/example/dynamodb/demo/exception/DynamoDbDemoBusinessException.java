package com.example.dynamodb.demo.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoDbDemoBusinessException extends DynamoDbDemoException {

    public DynamoDbDemoBusinessException(String message) {
        super(message);
        log.warn(message);
    }

    public DynamoDbDemoBusinessException(String message, Throwable throwable) {
        super(message, throwable);
        log.warn(message);
    }

    public DynamoDbDemoBusinessException(String message, Throwable throwable, Object... objects) {
        super(message, throwable);
        log.warn(message, objects);
    }

}
