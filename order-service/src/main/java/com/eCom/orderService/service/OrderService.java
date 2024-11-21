package com.eCom.orderService.service;

import com.eCom.orderService.client.InventoryClient;
import com.eCom.orderService.dto.OrderRequest;
import com.eCom.orderService.event.OrderPlacedEvent;
import com.eCom.orderService.model.Order;
import com.eCom.orderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        var isProductIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductIsInStock) {

            var order = mapToOrder(orderRequest);

            // Save to Order Repository
            orderRepository.save(order);

            // Send the message to Kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();

            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.email());
            orderPlacedEvent.setFirstName(orderRequest.firstName());
            orderPlacedEvent.setLastName(orderRequest.lastName());

            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic 'order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);

        }else {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is out of stock!");
        }

    }

    public static Order mapToOrder(OrderRequest orderRequest){

        // Map Order Request to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setFirstName(orderRequest.firstName());
        order.setLastName(orderRequest.lastName());
        order.setEmail(orderRequest.email());

        return order;
    }
}
