package com.alpha.ddms.services;

import com.alpha.ddms.configuration.ConfigProperties;
import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private SalesRepository salesRepository;

    public Page<CustomerModel> getAllCustomer(String dealerId, String customerName, Integer page,Integer limit){
        Page<CustomerModel> cm = new PageImpl<>(new ArrayList<>());
        if(limit == 0 || limit == null){
            limit = ConfigProperties.getConstant_max_limit();
        }
        if(page == null){
            page = 0;
        }
        if(!dealerRepository.existsById(dealerId)){
            return cm;
        }
        cm = customerRepository.getAllCustomer(dealerRepository.findById(dealerId).get(),
                customerName,
                PageRequest.of(page,limit));
        return cm;
    }

    public Optional<CustomerModel> findById(String id){
        return customerRepository.findById(id);
    }

    public void saveCustomer(CustomerRequestDto dto){
        CustomerModel save = new CustomerModel();
        save.setCustomer_id(dto.getCustomerId());
        save.setCustomer_name(dto.getCustomerName());
        save.setDealerModel(dealerRepository.findById(dto.getDealerId()).get());
        save.setCustomer_gender(dto.getCustomerGender());
        save.setCustomer_nik(dto.getCustomerNik());
        save.setCustomer_kk(dto.getCustomerKk());
        save.setCustomer_email(dto.getCustomerEmail());
        save.setCustomer_address(dto.getCustomerAddress());
        save.setCustomer_telp_number(dto.getCustomerTelp());
        save.setCustomer_hp_number(dto.getCustomerHp());
        save.setSalesModel(salesRepository.findById(dto.getSalesId()).get());
        save.setCustomer_status(dto.getCustomerStatus());
        customerRepository.save(save);
    }

    public CustomerModel updateCustomer(CustomerRequestDto dto){
        CustomerModel update = customerRepository.findById(dto.getCustomerId()).get();
        System.out.println(dto);
        if (!dto.getCustomerName().isEmpty()){
            update.setCustomer_name(dto.getCustomerName());//debug
        }
        if (!dto.getDealerId().isEmpty()){
            update.setDealerModel(dealerRepository.findById(dto.getDealerId()).get());
        }
        if (!dto.getCustomerGender().isEmpty()){
            update.setCustomer_gender(dto.getCustomerGender());
        }
        if ( !dto.getCustomerNik().isEmpty()){
            update.setCustomer_nik(dto.getCustomerNik());
        }
        if (!dto.getCustomerKk().isEmpty()){
            update.setCustomer_kk(dto.getCustomerKk());
        }
        if (!dto.getCustomerEmail().isEmpty()){
            update.setCustomer_email(dto.getCustomerEmail());
        }
        if ( !dto.getCustomerAddress().isEmpty()){
            update.setCustomer_address(dto.getCustomerAddress());
        }
        if ( !dto.getCustomerTelp().isEmpty()){
            update.setCustomer_telp_number(dto.getCustomerTelp());
        }
        if (!dto.getCustomerHp().isEmpty()){
            update.setCustomer_hp_number(dto.getCustomerHp());
        }
        if (!dto.getSalesId().isEmpty()){
            update.setSalesModel(salesRepository.findById(dto.getSalesId()).get());
        }
        if (!dto.getCustomerStatus().isEmpty()){
            update.setCustomer_status(dto.getCustomerStatus());
        }
        customerRepository.save(update);
        return update;
    }
}