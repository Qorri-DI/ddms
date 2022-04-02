package com.alpha.ddms.services;

import com.alpha.ddms.domains.OrderModel;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DealerRepository dealerRepository;

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

    public List<OrderModel> getAllOrder(String dealerId,String platNomor,
                                        String nomor_mesin,
                                        String nomor_rangka,
                                        String paymentStatus,
                                        Integer offset, Integer limit){
        Page<OrderModel> page = orderRepository.getAllOrder(dealerRepository.findById(dealerId).get(),
                platNomor,
                nomor_mesin,
                nomor_rangka,
                paymentStatus,
                PageRequest.of(offset,limit));
        System.out.println(page.getTotalElements());
        List<OrderModel> order = page.toList();
        return order;
    }

}