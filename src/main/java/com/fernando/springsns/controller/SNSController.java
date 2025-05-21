package com.fernando.springsns.controller;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {

    @Autowired
    private AmazonSNS amazonSNSClient;

    private String TOPIC_ARN = "arn:aws:sns:ap-southeast-2:000000000000:MyTopic";

    @GetMapping("/subscribe/{email}")
    public String subscribeTopic(@PathVariable("email") String email){
        SubscribeRequest subscribeRequest =
                new SubscribeRequest(TOPIC_ARN,"email",email);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Thanks.! Check your Email";
    }

    @GetMapping("/publish/{msg}")
    public String publishMessage(@PathVariable("msg") String msg){
        PublishRequest publishRequest =
                new PublishRequest(TOPIC_ARN,msg,"SpringBoot with Amazon SNS");
        amazonSNSClient.publish(publishRequest);
        return "Message Published.!";
    }
}
