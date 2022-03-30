package com.alpha.ddms.services;

import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    public SalesModel findSupervisorId(String supervisorId){
        Optional<SalesModel> salesModel = salesRepository.findSupervisorId(supervisorId);
        return (!salesModel.isPresent()) ? null : salesModel.get();
    }

    public SalesModel save(SalesModel data){
        return salesRepository.save(data);
    }

    public SalesModel findById(String salesId){
        Optional<SalesModel> salesModel = salesRepository.findById(salesId);
        return !salesModel.isPresent() ? null : salesModel.get();
    }

    public String findLatestId(String salesId){
        Optional<SalesModel> salesModel = salesRepository.findLatestId(salesId);
        return !salesModel.isPresent() ? null : salesModel.get().getSales_id();
    }

    public List<SalesModel> searchSales(String salesName, String dealerId, String salesStatus, int offset, int limit){
        return salesRepository.searchSalesModel(salesName, dealerId, salesStatus, offset, limit);
    }
}
