package com.fernando.springsns.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AmazonSNSConfig {

    @Primary
    @Bean
    public AmazonSNS amazonSNS(AwsClientBuilder.EndpointConfiguration endpointConfig) {
        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(endpointConfig)
                .withCredentials(
                        // LocalStack accepts anything, so dummy creds are fine
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials("test", "test")
                        )
                )
                .build();
    }

    /**
     * Centralize endpoint + region in a reusable bean.
     */
    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfig() {
        return new AwsClientBuilder.EndpointConfiguration(
                "http://localhost:4566",
                "ap-southeast-2"
        );
    }
}