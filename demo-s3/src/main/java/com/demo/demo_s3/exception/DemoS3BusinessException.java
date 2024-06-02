package com.demo.demo_s3.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoS3BusinessException extends DemoS3Exception{

    public DemoS3BusinessException(String message, Throwable cause) {
        super(message, cause);
        log.warn(message);
    }

    public DemoS3BusinessException(String message, Throwable cause, Object... objects) {
        super(message, cause);
        log.warn(message, ":", objects);
    }

}
