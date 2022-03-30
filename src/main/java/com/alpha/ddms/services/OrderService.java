package com.alpha.ddms.services;

import com.alpha.ddms.domains.OrderModel;
import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.repositories.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public String findLatestId(String orderId){
        Optional<OrderModel> orderModel = orderRepository.findLatestId(orderId);
        return !orderModel.isPresent() ? null : orderModel.get().getOrder_id();
    }

    public OrderModel save(OrderModel data){
        return orderRepository.save(data);
    }

    public Optional<OrderModel> findById(String orderId){
        return orderRepository.findById(orderId);
    }
}
