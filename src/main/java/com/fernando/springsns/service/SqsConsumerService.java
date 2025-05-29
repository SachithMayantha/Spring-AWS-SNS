package com.fernando.springsns.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqsConsumerService {

    private final AmazonSQS sqsClient;

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    public SqsConsumerService(AmazonSQS sqsClient) {
        this.sqsClient = sqsClient;
    }

    // Poll the queue every 5 seconds
    @Scheduled(fixedDelay = 5000)
    public void pollQueue() {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
                .withMaxNumberOfMessages(5)
                .withWaitTimeSeconds(1);

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();

        for (Message message : messages) {
            System.out.println("Received message: " + message.getBody());
            // TODO: process the message (save to DB, trigger logic, etc.)

            // Delete after processing
            sqsClient.deleteMessage(queueUrl, message.getReceiptHandle());
        }
    }
}