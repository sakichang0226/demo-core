package com.demo.demo_s3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    public Map<String, String> getYamlFile() throws IOException {
        GetObjectRequest request = new GetObjectRequest("spring-testbuket-2024", "test.yaml");
        S3Object object = s3Client.getObject(request);

        try (S3ObjectInputStream reader = object.getObjectContent()) {
            Yaml yaml = new Yaml();
            Map<String,String> response =  (Map<String, String>) yaml.load(reader);

            return response;
        }
    }
}
