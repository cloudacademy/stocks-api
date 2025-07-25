package com.cloudacademy.stocks.config;

import net.snowflake.client.jdbc.SnowflakeBasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Configuration
public class SnowflakeDataSourceConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        // Load private key from resources
        byte[] keyBytes = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("keys/pvt_key.p8").toURI()));
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));

        SnowflakeBasicDataSource dataSource = new SnowflakeBasicDataSource();
        // dataSource.setUrl("jdbc:snowflake://ezc84634.us-east-1.snowflakecomputing.com");
        // dataSource.setUser("JAVADEMO");
        // dataSource.setWarehouse("COMPUTE_WH");
        // dataSource.setDatabaseName("DEMODB_STOCKS");
        // dataSource.setSchema("PUBLIC");
        // dataSource.setRole("JAVASTOCKS");

        dataSource.setUrl(System.getenv("SNOWFLAKE_JAVA_URL"));
        dataSource.setUser(System.getenv("SNOWFLAKE_JAVA_USER"));
        dataSource.setWarehouse(System.getenv("SNOWFLAKE_JAVA_WH"));
        dataSource.setDatabaseName(System.getenv("SNOWFLAKE_JAVA_DB"));
        dataSource.setSchema(System.getenv("SNOWFLAKE_JAVA_SCHEMA"));
        dataSource.setRole(System.getenv("SNOWFLAKE_JAVA_ROLE"));

        dataSource.setPrivateKey(privateKey);
        return dataSource;
    }
}
