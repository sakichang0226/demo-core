package com.demo.demo_s3.Service;

import com.demo.demo_s3.client.DemoS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final DemoS3Client client ;

    public Map<String, String> getYamlFile() {
        return client.getObjectFromYaml("spring-testbuket-2024","test");
    }
}
