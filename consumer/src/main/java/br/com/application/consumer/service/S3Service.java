package br.com.application.consumer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    public void uploadFile(String bucketName, String key, String filePath) {
        File file = new File(filePath);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);

        PutObjectResult response = s3Client.putObject(putObjectRequest);
        logger.info("Arquivo foi colocado na nuvem.");
    }
}
