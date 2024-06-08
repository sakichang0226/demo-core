package com.example.dynamodb.demo.constant;

import lombok.Data;

public enum DynamoDBError {

    DYNAMO_DB_ERROR001("DYNAMO_DB_ERR001", "DynamoDB接続時に問題が発生しました."),
    DYNAMO_DB_ERROR002("DYNAMO_DB_ERR002", "対象の項目が見つかりませんでした.");

    private final String code;

    private final String message;

    private DynamoDBError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
