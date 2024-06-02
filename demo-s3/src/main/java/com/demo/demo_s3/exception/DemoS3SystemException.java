package com.demo.demo_s3.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * s3プロジェクト内のERRORレベル相当の例外を扱う
 */
@Slf4j
public class DemoS3SystemException extends DemoS3Exception {

    public DemoS3SystemException(String message, Throwable cause){
        super(message, cause);
        log.error(message);
    }

    public DemoS3SystemException(String message, Throwable cause, Object... objects){
        super(message, cause, objects);
        log.error(message, ":", objects);
    }

}
