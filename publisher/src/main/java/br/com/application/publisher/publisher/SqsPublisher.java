package br.com.application.publisher.publisher;

import br.com.application.model.Order;
import br.com.application.publisher.config.SqsConfig;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqsPublisher {

    private static final Logger logger = LoggerFactory.getLogger(SqsPublisher.class);

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private SqsConfig sqsConfig;

    public void publishMessage(Order order) {
        logger.info("Mensagem publicada com o ID: {}", order.getId());
        sqsTemplate.send(sqsConfig.getUrl(), order);
    }
}
