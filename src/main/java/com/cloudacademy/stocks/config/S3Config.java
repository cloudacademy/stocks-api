package com.cloudacademy.stocks.config;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudacademy.stocks.utils.S3Initializer;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentialsProviderChain providerChain = new DefaultAWSCredentialsProviderChain();
        return AmazonS3ClientBuilder.standard()
                .withCredentials(providerChain)
                .withRegion(Regions.US_WEST_2)
                .build();
    }
    
    public S3Initializer s3Initializer(AmazonS3 amazonS3) {
        return new S3Initializer(amazonS3);
    }
}
