package com.alpha.ddms.services;

import com.alpha.ddms.DTO.DealerDTO;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.repositories.DealerRepository;
import com.alpha.ddms.repositories.SalesRepository;
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

    public Optional<DealerModel> findByCode(String dealer_code) {
        return dealerRepository.findByCode(dealer_code);
    }
    public Optional<DealerModel> findByName(String dealer_name) {
        return dealerRepository.findByName(dealer_name);
    }

    public int saveDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code){
        return dealerRepository.saveDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code);
    }

    public int updateDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code){
       return dealerRepository.updateDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code);
    }
    public DealerModel save(DealerModel data){
        return dealerRepository.save(data);
    }
    public List<DealerDTO> listAll(String dealer_code,String dealer_status, String dealer_name, int offset, int limit){
        Page<DealerModel> getAll = dealerRepository.getAllPage((PageRequest.of(offset, limit)),dealer_code,dealer_status,dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<DealerModel> dealerModels= getAll.toList();

        for (DealerModel dealerModel : dealerModels){
            DealerDTO dealerDTO = new DealerDTO();

            dealerDTO.setDealerId(dealerModel.getDealer_code());
            dealerDTO.setDealerName(dealerModel.getDealer_name());
            dealerDTO.setDealerClass(dealerModel.getDealer_class());
            dealerDTO.setTelpNumber(dealerModel.getTelp_number());
            dealerDTO.setAlamat(dealerModel.getAlamat());
            dealerDTO.setDealerExCode(dealerModel.getDealer_ext_code());
            dealerDTO.setDealerStatus(dealerModel.getDealer_status());
            dealerDTOList.add(dealerDTO);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> dealerById (String dealerId){
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<DealerModel> ById =dealerRepository.findByDealerId(dealerId);

        for(DealerModel dealerModel: ById) {

            DealerDTO dealerDTO = new DealerDTO();
            dealerDTO.setDealerId(dealerId);
            dealerDTO.setDealerName(dealerModel.getDealer_name());
            dealerDTO.setDealerClass(dealerModel.getDealer_class());
            dealerDTO.setTelpNumber(dealerModel.getTelp_number());
            dealerDTO.setAlamat(dealerModel.getAlamat());
            dealerDTO.setDealerExCode(dealerModel.getDealer_ext_code());
            dealerDTO.setDealerStatus(dealerModel.getDealer_status());

            dealerDTOList.add(dealerDTO);
        }
        return dealerDTOList;
    }

}
