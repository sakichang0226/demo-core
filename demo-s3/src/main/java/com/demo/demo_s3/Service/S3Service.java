package com.demo.demo_s3.Service;

import com.demo.demo_s3.client.DemoS3Client;
import com.demo.demo_s3.constant.S3BucketEndPoint;
import com.demo.demo_s3.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final DemoS3Client client ;

    public Map<String, String> getYamlFile() {
        return client.getObjectFromYaml(S3BucketEndPoint.TEST,"test");
    }

    public Test getTest() {
        return client.getObjectFromFile(S3BucketEndPoint.TEST, "test2", Test.class);
    }
}
