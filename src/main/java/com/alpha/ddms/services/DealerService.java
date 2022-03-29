package com.alpha.ddms.services;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;
    public DealerModel getDealer(String idDealer){
        Optional<DealerModel> data = dealerRepository.findById(idDealer);
        return data.isEmpty() ? null : data.get();
    }
}
