package com.demo.demo_s3.Service;

import com.demo.demo_s3.client.DemoS3Client;
import com.demo.demo_s3.constant.S3BucketEndPoint;
import com.demo.demo_s3.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * S3のクライアントを呼び出し、オブジェクトに変換するためのサービス層<br>
 * 親プロジェクト側からサービス層を呼び出し処理をする
 */
@Service
@RequiredArgsConstructor
public class S3Service {

    private final DemoS3Client client ;

    /**
     * Yamlファイルからオブジェクトにパースする
     * @return yamlのキー,バリューの組み合わせ
     */
    public Map<String, String> getYamlFile() {
        return client.getObjectFromYaml(S3BucketEndPoint.TEST,"test");
    }

    /**
     * S3のJSONファイルから、単体のオブジェクトにパースする
     * @return Testクラス
     */
    public Test getTest() {
        return client.getObjectFromFile(S3BucketEndPoint.TEST, "test2", Test.class);
    }

    /**
     * S3のJSONファイルを取得し、複数のTestオブジェクトの項目を返す
     * @return Testクラスのリスト
     */
    public List<Test> getTestList() {
        return client.getObjectFromFile(S3BucketEndPoint.TEST, "test3");
    }
}
