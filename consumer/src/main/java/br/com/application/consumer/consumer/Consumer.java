package br.com.application.consumer.consumer;

import br.com.application.consumer.service.OrderService;
import br.com.application.model.Order;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    Gson gson = new Gson();

    @Autowired
    OrderService orderService;

    @SqsListener("minha-fila")
    public void listen(Order order) throws IOException {
        logger.info("Mensagem recebida: {}", gson.toJson(order));
        orderService.orderProcess(order);
    }
}
