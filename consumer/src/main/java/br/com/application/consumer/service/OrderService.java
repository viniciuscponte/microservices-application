package br.com.application.consumer.service;

import br.com.application.consumer.entity.OrderEntity;
import br.com.application.consumer.repository.OrderRepository;
import br.com.application.model.Order;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    Gson gson = new Gson();

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    S3Service s3Service;

    public void orderProcess(Order order) throws IOException {
        OrderEntity orderEntity = saveOrder(convertOrderToOrderEntity(order));
        pushToBucket(orderEntity);
    }

    public OrderEntity saveOrder(OrderEntity orderEntity){
        return orderRepository.save(orderEntity);
    }

    private OrderEntity convertOrderToOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setValue(order.getValue());
        orderEntity.setDescription(order.getDescription());

        return orderEntity;
    }

    public List<OrderEntity> getOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findOrderEntityById(id);
    }

    private void pushToBucket(OrderEntity orderEntity) throws IOException {
        createDirectory("./data");

        String fileName = "order-" + orderEntity.getId() + ".json";
        String filePath = "./data/" + fileName;

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(orderEntity, fileWriter);
            logger.info("Arquivo JSON criado com sucesso.");
        }
        s3Service.uploadFile("order-bucket", fileName, filePath);

        File file = new File(filePath);
        file.delete();
    }

    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria o diretório, incluindo subdiretórios
        }
    }
}
