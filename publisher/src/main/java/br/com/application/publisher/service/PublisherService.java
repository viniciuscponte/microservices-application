package br.com.application.publisher.service;

import br.com.application.model.Order;
import br.com.application.publisher.publisher.SqsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Autowired
    private SqsPublisher sqsPublisher;

    public void publishMessage(Order order) {
        sqsPublisher.publishMessage(order);
    }
}
