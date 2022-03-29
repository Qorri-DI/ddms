package com.alpha.ddms.services;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.repositories.DealerRepository;
import com.alpha.ddms.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;

    public Optional<DealerModel> findByCode(String dealer_code) {
        return dealerRepository.findByCode(dealer_code);
    }
    public Optional<DealerModel> findByName(String dealer_name) {
        return dealerRepository.findByName(dealer_name);
    }

    public int saveDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code){
        return dealerRepository.saveDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code);
    }
    public int updateDealer (String dealer_code,String dealer_name){
       return dealerRepository.updateDealer(dealer_code,dealer_name);
    }

}
