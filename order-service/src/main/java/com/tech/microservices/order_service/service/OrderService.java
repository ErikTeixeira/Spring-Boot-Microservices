package com.tech.microservices.order_service.service;

import com.tech.microservices.order_service.dto.OrderRequest;
import com.tech.microservices.order_service.model.Order;
import com.tech.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
// colocar um construtor para o orderRepository - para servir como se colocasse o @Autowired
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        // Map orderRquest to order object
        Order order = new Order();

            // para criar um único número de pedido
        order.setOrderNumber( UUID.randomUUID().toString() );
        order.setPrice( orderRequest.price() );
        order.setSkuCode( orderRequest.skuCode() );
        order.setQuantity( orderRequest.quantity() );

        // then save order to orderRepository
        orderRepository.save(order);
    }


}
