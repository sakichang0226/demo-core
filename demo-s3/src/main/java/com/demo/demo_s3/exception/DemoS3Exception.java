package com.demo.demo_s3.exception;

/**
 *
 */
public class DemoS3Exception extends RuntimeException {

    public DemoS3Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoS3Exception(String message, Throwable cause, Object... objects) {
        super(message, cause);
    }

}
