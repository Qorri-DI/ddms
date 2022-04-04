package com.alpha.ddms.services;

import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.models.ViewDealer;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.*;
import java.util.*;

@Service
@Transactional
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;
    @Autowired
    ViewDealerRepository viewDealerRepository;

    public Optional<DealerModel> findByDealerCode(String dealer_code) {
        return dealerRepository.findById(dealer_code);
    }

    public Optional<DealerModel> findByGetId(String dealerId) {
        return dealerRepository.findByCode(dealerId);
    }
    public Optional<ViewDealer> findByGetIdView(String dealerId) {
        return viewDealerRepository.findByCodeView(dealerId);
    }

    public DealerModel save(DealerModel data){
        return dealerRepository.save(data);
    }
	
	public int saveDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code){
        return dealerRepository.saveDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code);
    }
	
	public int updateDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code){
        return dealerRepository.updateDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code);
    }

    public List<DealerDTO> listAllAAA(String dealer_code,String dealer_status, String dealer_name, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllAAA((PageRequest.of(offset, limit)),dealer_code,dealer_status,dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllAAK(String dealer_code, String dealer_name, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllAAK((PageRequest.of(offset, limit)),dealer_code,dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllAKA(String dealer_code,String dealer_status, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllAKA((PageRequest.of(offset, limit)),dealer_code,dealer_status);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllAKK(String dealer_code, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllAKK((PageRequest.of(offset, limit)),dealer_code);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllKAA(String dealer_status, String dealer_name, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllKAA((PageRequest.of(offset, limit)),dealer_status,dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllKAK(String dealer_name, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllKAK((PageRequest.of(offset, limit)),dealer_name);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }
    public List<DealerDTO> listAllKKA(String dealer_status, int offset, int limit){
        Page<ViewDealer> getAll = viewDealerRepository.getAllKKA((PageRequest.of(offset, limit)),dealer_status);
        List<DealerDTO> dealerDTOList = new ArrayList<>();
        List<ViewDealer> dealerModels= getAll.toList();

        for (ViewDealer viewDealer : dealerModels){
            DealerDTO dd = new DealerDTO();

            dd.setDealerId(viewDealer.getDealer_code());
            dd.setDealerName(viewDealer.getDealer_name());
            dd.setDealerClass(viewDealer.getDealer_class());
            dd.setTelpNumber(viewDealer.getTelp_number());
            dd.setAlamat(viewDealer.getAlamat());
            dd.setDealerExCode(viewDealer.getDealer_ext_code());
            dd.setDealerStatus(viewDealer.getDealer_status());
            dealerDTOList.add(dd);
        }
        return dealerDTOList;
    }

    public DealerDTO dealerById (String dealerId){
        ViewDealer ById =viewDealerRepository.findByDealerId(dealerId);
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

    public Optional<DealerModel> findById(String dealerId){
        Optional<DealerModel> dealerModel = dealerRepository.findById(dealerId);
        return dealerModel;
    }
}
