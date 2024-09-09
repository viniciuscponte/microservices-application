package br.com.application.consumer.controller;

import br.com.application.consumer.entity.OrderEntity;
import br.com.application.consumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderEntity> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
}
