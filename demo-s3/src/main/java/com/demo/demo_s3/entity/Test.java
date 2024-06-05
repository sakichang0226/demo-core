package com.demo.demo_s3.entity;

import lombok.Data;

/**
 * S3から取得した内容を変換する入れ物クラス
 */
@Data
public class Test {

    /** id */
    int id;

    /** 投稿タイトル */
    String title;

    /** 投稿した内容 */
    String message;
}
