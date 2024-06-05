package com.demo.demo_s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;

import com.demo.demo_s3.client.DemoS3Client;
import com.demo.demo_s3.constant.S3BucketEndPoint;
import com.demo.demo_s3.exception.DemoS3BusinessException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DemoS3ClientTest {

    AmazonS3 s3 = mock(AmazonS3.class);

    @InjectMocks
    DemoS3Client client = new DemoS3Client(s3);

    @Test
    public void test_getObjectFromYaml_S3のファイルが見つからないとき適切な例外クラスが返されるか() {
        when(s3.getObject(any())).thenThrow(AmazonS3Exception.class);

        assertThrows(DemoS3BusinessException.class, () -> client.getObjectFromYaml(S3BucketEndPoint.TEST, "test"));
    }

    @Test
    public void test_getObjectFromFile_S3のファイルが見つからないとき適切な例外クラスが返されるか() {
        when(s3.getObject(any())).thenThrow(AmazonS3Exception.class);

        assertThrows(DemoS3BusinessException.class, () -> client.getObjectFromFile(S3BucketEndPoint.TEST, "test", com.demo.demo_s3.entity.Test.class));
    }

    @Test
    public void test_getObjectFromFile_list_S3のファイルが見つからないとき適切な例外クラスが返されるか() {
        when(s3.getObject(any())).thenThrow(AmazonS3Exception.class);

        assertThrows(DemoS3BusinessException.class, () -> client.getObjectFromFile(S3BucketEndPoint.TEST, "test"));
    }

    @Test
    public void test_getObjectFromFile_対象ファイルのパース処理失敗時適切な例外を返すか() throws FileNotFoundException {
        InputStream file = new FileInputStream("src/test/resources/test2.json");
        S3Object object = new S3Object();
        object.setObjectContent(file);

        when(s3.getObject(any())).thenReturn(object);

        assertThrows(DemoS3BusinessException.class, () -> client.getObjectFromFile(S3BucketEndPoint.TEST, "test2", com.demo.demo_s3.entity.Test.class));
    }

    @Test
    public void test_getObjectFromFile_list_対象ファイルのパース処理失敗時適切な例外を返すか() throws FileNotFoundException {
        InputStream file = new FileInputStream("src/test/resources/test3.json");
        S3Object object = new S3Object();
        object.setObjectContent(file);

        when(s3.getObject(any())).thenReturn(object);

        assertThrows(DemoS3BusinessException.class, () -> client.getObjectFromFile(S3BucketEndPoint.TEST, "test3"));
    }

}
