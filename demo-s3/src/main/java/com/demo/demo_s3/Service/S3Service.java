package com.demo.demo_s3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    public void get() throws AmazonS3Exception {
        GetObjectRequest request = new GetObjectRequest("", "");
        S3Object object = s3Client.getObject(request);

        object.getObjectContent();
    }
}
