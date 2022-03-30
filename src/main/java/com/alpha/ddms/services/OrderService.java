package com.alpha.ddms.services;

import com.alpha.ddms.domains.OrderModel;
import com.alpha.ddms.repositories.DealerRepository;
import com.alpha.ddms.repositories.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DealerRepository dealerRepository;

    public List<OrderModel> getAllOeder(String dealerId,String platNomor,
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
        List<OrderModel> order = page.toList();
        return order;
    }

}
