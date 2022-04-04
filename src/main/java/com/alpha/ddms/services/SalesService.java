package com.alpha.ddms.services;

import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.models.ViewSales;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SalesService {

    @Autowired SalesRepository salesRepository;
    @Autowired ViewSalesRepository viewSalesRepository;

    public SalesModel findSupervisorId(String supervisorId){
        Optional<SalesModel> salesModel = salesRepository.findSupervisorId(supervisorId);
        return (!salesModel.isPresent()) ? null : salesModel.get();
    }

    public SalesModel save(SalesModel data){
        return salesRepository.save(data);
    }

    public Optional<SalesModel> findById(String salesId){
        Optional<SalesModel> salesModel = salesRepository.findById(salesId);
        return salesModel;
    }

    public Optional<ViewSales> findViewById(String salesId){
        Optional<ViewSales> viewSalesModel = viewSalesRepository.findViewById(salesId);
        return viewSalesModel;
    }
    public String findLatestId(String salesId){
        Optional<SalesModel> salesModel = salesRepository.findLatestId(salesId);
        return !salesModel.isPresent() ? null : salesModel.get().getSales_id();
    }

//    public List<SalesModel> searchSales(String salesName, String dealerId, String salesStatus, int offset, int limit){
//        return salesRepository.searchSalesModel(salesName, dealerId, salesStatus, offset, limit);
//    }

    public Page<ViewSales> searchSales(String salesName, String dealerId, String salesStatus, int page, int limit){
        return viewSalesRepository.searchSalesModel(salesName, dealerId, salesStatus, PageRequest.of(page,limit));
    }
}