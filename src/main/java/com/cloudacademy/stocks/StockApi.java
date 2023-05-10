package com.cloudacademy.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.cloudacademy.stocks.utils.S3Initializer;

@SpringBootApplication
public class StockApi {
	@Autowired
    private static Environment env;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StockApi.class, args);
        S3Initializer s3Initializer = context.getBean(S3Initializer.class);
		s3Initializer.init(StockApi.env.getProperty("aws.s3.bucket"));
		SpringApplication.run(StockApi.class, args);
	}
}
