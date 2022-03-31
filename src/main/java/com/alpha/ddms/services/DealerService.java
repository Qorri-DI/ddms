package com.alpha.ddms.services;

import com.alpha.ddms.dto.DealerDTO;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;

    public Optional<DealerModel> findByDealerCode(String dealer_code) {
        return dealerRepository.findById(dealer_code);
    }
    public Optional<DealerModel> findByGetId(String dealerId) {
        return dealerRepository.findByCode(dealerId);
    }

    public DealerModel save(DealerModel data){
        return dealerRepository.save(data);
    }
    public List<DealerDTO> listAll(String dealer_code,String dealer_status, String dealer_name, int offset, int limit){
        Page<DealerModel> getAll = dealerRepository.getAllPage((PageRequest.of(offset, limit)),dealer_code,dealer_status,dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<DealerModel> dealerModels= getAll.toList();


        for (DealerModel dealerModel : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(dealerModel.getDealer_code());
            dd.setDealerName(dealerModel.getDealer_name());
            dd.setDealerClass(dealerModel.getDealer_class());
            dd.setTelpNumber(dealerModel.getTelp_number());
            dd.setAlamat(dealerModel.getAlamat());
            dd.setDealerExCode(dealerModel.getDealer_ext_code());
            dd.setDealerStatus(dealerModel.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public DealerDTO dealerById (String dealerId){
        DealerModel ById =dealerRepository.findByDealerId(dealerId);
        DealerDTO ddo = new DealerDTO();

        ddo.setDealerId(dealerId);
        ddo.setDealerName(ById.getDealer_name());
        ddo.setDealerClass(ById.getDealer_class());
        ddo.setTelpNumber(ById.getTelp_number());
        ddo.setAlamat(ById.getAlamat());
        ddo.setDealerExCode(ById.getDealer_ext_code());
        ddo.setDealerStatus(ById.getDealer_status());

        return ddo;
    }
}
