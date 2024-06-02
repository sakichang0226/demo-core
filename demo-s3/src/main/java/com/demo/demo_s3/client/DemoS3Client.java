package com.demo.demo_s3.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.demo.demo_s3.constant.S3Error;
import com.demo.demo_s3.exception.DemoS3BusinessException;
import com.demo.demo_s3.exception.DemoS3SystemException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;

import static com.demo.demo_s3.constant.S3Error.*;


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
    @SuppressWarnings("unchecked")
    public <T> T getObjectFromYaml(String bucketName, String key) {

        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, key + ".yaml");
            S3Object object = s3Client.getObject(request);
            
            S3ObjectInputStream reader = object.getObjectContent();
            Yaml yaml = new Yaml();

            return (T) yaml.load(reader);

        } catch (AmazonS3Exception e) {
            throw new DemoS3BusinessException(
                    S3_ERR001.getCode() + ":" +  S3_ERR001.getMessage(),
                    e.getCause(), key + ".yaml");
        }

    }

    /**
     * S3のバケットからオブジェクトを取得し、指定したクラスオブジェクトに変換する
     * @param bucketName バケット名
     * @param key　ファイル名
     * @return 変換したいオブジェクトクラス
     */
    public <T> T getObjectFromFile(String bucketName, String key, Class<T> clazz)  {

        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, key + ".json");
            S3Object object = s3Client.getObject(request);

            S3ObjectInputStream reader = object.getObjectContent();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(reader, clazz);
        } catch (AmazonS3Exception e) {
            throw new DemoS3BusinessException(
                    S3_ERR001.getCode() + ":" +  S3_ERR001.getMessage(),
                    e.getCause(), key + ".json");
        } catch (JsonParseException e) {
            throw new DemoS3BusinessException(
                    S3_ERR002.getCode() + ":" +  S3_ERR002.getMessage(),
                    e.getCause());
        } catch (IOException e) {
            throw new DemoS3SystemException(
                    S3_ERR003.getCode() + ":" +  S3_ERR003.getMessage(),
                    e.getCause());
        }

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
        } catch (AmazonS3Exception e) {
            throw new DemoS3BusinessException(
                    S3_ERR001.getCode() + ":" +  S3_ERR001.getMessage(),
                    e.getCause(), key + ".json");
        } catch (JsonParseException e) {
            throw new DemoS3BusinessException(
                    S3_ERR002.getCode() + ":" +  S3_ERR002.getMessage(),
                    e.getCause());
        } catch (IOException e) {
            throw new DemoS3SystemException(
                    S3_ERR003.getCode() + ":" +  S3_ERR003.getMessage(),
                    e.getCause());
        }

    }

}
