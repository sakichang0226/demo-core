package com.demo.demo_s3.constant;

public enum S3Error {

    S3_ERR001("S3_ERR001","S3に対象のデータが見つかりません"),
    S3_ERR002("S3_ERR002", "対象ファイルのパース処理に失敗しました"),
    S3_ERR003("S3_ERR003","対象ファイルの入出力に失敗しました");

    private final String code;

    private final String message;

    private S3Error(String code, String message) {
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
