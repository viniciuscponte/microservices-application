package br.com.application.consumer.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${localstack.endpoint}")
    String localStackUrl;

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3Client.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localStackUrl, "sa-east-1"))
                .withPathStyleAccessEnabled(true) // Importante para Localstack
                .build();
    }
}
