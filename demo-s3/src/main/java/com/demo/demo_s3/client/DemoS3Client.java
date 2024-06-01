package com.demo.demo_s3.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;


/**
 * S3からファイルを取得し、オブジェクトに変換するためのクライアントクラス
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DemoS3Client {

    private final AmazonS3 s3Client;

    /**
     * S3のバケットからオブジェクトを取得し、指定したクラスオブジェクトに変換する
     * @param bucketName バケット名
     * @param key　ファイル名
     * @return 変換したいオブジェクトクラス
     */
    public <T> T getObjectFromYaml(String bucketName, String key) {

        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, key + ".yaml");
            S3Object object = s3Client.getObject(request);

            S3ObjectInputStream reader = object.getObjectContent();
            Yaml yaml = new Yaml();

            return (T) yaml.load(reader);

        } catch (AmazonS3Exception e) {
            log.warn(e.getMessage());
        }

        return null;
    }

    /**
     * S3のバケットからオブジェクトを取得し、指定したクラスオブジェクトに変換する
     * @param bucketName バケット名
     * @param key　ファイル名
     * @return 変換したいオブジェクトクラス
     */
    public <T> T getObjectFromFile(String bucketName, String key, Class<T> clazz) {

        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, key + ".json");
            S3Object object = s3Client.getObject(request);

            S3ObjectInputStream reader = object.getObjectContent();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(reader, clazz);
        } catch (AmazonS3Exception | IOException e) {
            log.warn(e.getMessage());
        }

        return null;
    }

    /**
     * S3のバケットからオブジェクトを取得し、指定したクラスオブジェクトのコレクションクラスに変換する
     * @param bucketName バケット名
     * @param key　ファイル名
     * @return 変換したいオブジェクトクラスのコレクションクラス
     */
    public <T> T getObjectFromFile(String bucketName, String key) {

        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, key + ".json");
            S3Object object = s3Client.getObject(request);

            S3ObjectInputStream reader = object.getObjectContent();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(reader, new TypeReference<T>() {});
        } catch (AmazonS3Exception | IOException e) {
            log.warn(e.getMessage());
        }

        return null;
    }

}
