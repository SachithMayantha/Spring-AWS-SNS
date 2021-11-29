package com.fernando.springsns.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AmazonSNSConfig {

    @Primary
    @Bean
    public AmazonSNSClient amazonSNSClient(){
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "AKIA6QOLNDYH3WMT2YNF",
                                        "B8U2j8ANa2l4ZEzBm6H81vDjXecLz1vsvJ0T+gdb"
                                )
                        )
                )
                .build();
    }
}
