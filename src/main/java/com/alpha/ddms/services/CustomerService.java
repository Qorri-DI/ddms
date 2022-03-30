package com.alpha.ddms.services;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
import com.alpha.ddms.repositories.CustomerRepository;
import com.alpha.ddms.repositories.DealerRepository;
import com.alpha.ddms.repositories.SalesRepository;
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
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private SalesRepository salesRepository;

    public List<CustomerModel> getAllCustomer(Integer page,Integer limit){
        Page<CustomerModel> cm = customerRepository.getAllCustomer(PageRequest.of(page,limit));
        List<CustomerModel> customer = cm.toList();
        return customer;
    }

    public Optional<CustomerModel> getCustomer(String id){
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

    public void updateCustomer(CustomerRequestDto dto){
        CustomerModel update = customerRepository.findById(dto.getCustomerId()).get();
        if (dto.getCustomerName() != null){
            update.setCustomer_name(dto.getCustomerName());
        }
        if (dto.getDealerId() != null){
            update.setDealerModel(dealerRepository.findById(dto.getDealerId()).get());
        }
        if (dto.getCustomerGender() != null){
            update.setCustomer_name(dto.getCustomerGender());
        }
        if (dto.getCustomerNik() != null){
            update.setCustomer_nik(dto.getCustomerNik());
        }
        if (dto.getCustomerKk() != null){
            update.setCustomer_kk(dto.getCustomerKk());
        }
        if (dto.getCustomerEmail() != null){
            update.setCustomer_email(dto.getCustomerEmail());
        }
        if (dto.getCustomerAddress() != null){
            update.setCustomer_address(dto.getCustomerAddress());
        }
        if (dto.getCustomerTelp() != null){
            update.setCustomer_telp_number(dto.getCustomerTelp());
        }
        if (dto.getCustomerHp() != null){
            update.setCustomer_hp_number(dto.getCustomerHp());
        }
        if (dto.getSalesId() != null){
            update.setSalesModel(salesRepository.findById(dto.getSalesId()).get());
        }
        if (dto.getCustomerStatus() != null){
            update.setCustomer_status(dto.getCustomerStatus());
        }
        customerRepository.save(update);
    }
}
