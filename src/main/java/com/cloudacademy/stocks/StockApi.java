package com.cloudacademy.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.cloudacademy.stocks.utils.S3Initializer;

@SpringBootApplication
public class StockApi {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StockApi.class, args);
        S3Initializer s3Initializer = context.getBean(S3Initializer.class);
		String bucketName = context.getEnvironment().getProperty("aws.s3.bucket");
		s3Initializer.init(bucketName);
	}
}
