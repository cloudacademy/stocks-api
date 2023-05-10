package com.cloudacademy.stocks.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Initializer {

    private final AmazonS3 amazonS3;

    @Autowired
    public S3Initializer(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void init(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            Bucket bucket = amazonS3.createBucket(bucketName);
        }
    }
}
