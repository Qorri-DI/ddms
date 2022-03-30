package com.alpha.ddms.services;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Optional<CustomerModel> findById(String customerId){
        return customerRepository.findById(customerId);
    }
}