package com.alpha.ddms.services;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository cr;


    public List<CustomerModel> getAllCustomer(Integer page,Integer limit){
        Page<CustomerModel> cm = cr.getAllCustomer(PageRequest.of(page,limit));
        List<CustomerModel> customer = cm.toList();
        return customer;
    }

    public CustomerModel getCustomer(String id){
        return cr.findById(id).get();
    }
}
