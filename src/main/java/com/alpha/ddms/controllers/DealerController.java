package com.alpha.ddms.controllers;

import com.alpha.ddms.DTO.*;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;

    @PostMapping("save")
    public ResponseEntity<Object> saveDealer(
            @RequestBody final Map<String, Object> req
    ) {
        Map<String,Object> response = new HashMap<>();
        String dealer_code = req.get("dealerId").toString();
        String dealer_name = req.get("dealerName").toString();
        String dealer_class = req.get("dealerClass").toString();
        String telp_number = req.get("telpNumber").toString();
        String alamat = req.get("alamat").toString();
        String dealer_ext_code = req.get("dealerExtCode").toString();
        String dealer_status = req.get("dealerStatus").toString();

        DealerDTO dto = new DealerDTO();

        Optional<DealerModel> dealerCode = dealerService.findByCode(dealer_code);
        Optional<DealerModel> dealerName = dealerService.findByName(dealer_name);

        if (!dealerCode.isPresent() || !dealerName.isPresent()) {
            DealerModel dealerModel= new DealerModel();
            dealerModel.setDealer_code(dealer_code);
            dealerModel.setDealer_name(dealer_name);
            dealerModel.setDealer_class(dealer_class);
            dealerModel.setTelp_number(telp_number);
            dealerModel.setAlamat(alamat);
            dealerModel.setDealer_ext_code(dealer_ext_code);
            dealerModel.setDealer_status(dealer_status);
            dealerService.save(dealerModel);

            List<DealerDTO> dtoList = new ArrayList<>();
            dto.setDealerId(dealer_code);
            dto.setDealerName(dealer_name);
            dto.setDealerClass(dealer_class);
            dto.setTelpNumber(telp_number);
            dto.setAlamat(alamat);
            dto.setDealerExCode(dealer_ext_code);
            dto.setDealerStatus(dealer_status);
            dtoList.add(dto);

            DealerDtoPost dealerDtoPost= new DealerDtoPost();
            dealerDtoPost.setCode(201);
            dealerDtoPost.setData(dtoList);
            dealerDtoPost.setMassage("Process Successed");
            dealerDtoPost.setStatus("S");

            return new ResponseEntity<>(dealerDtoPost, HttpStatus.OK);

        } else {
            DealerModel dealerModel= new DealerModel();
            dealerModel.setDealer_code(dealer_code);
            dealerModel.setDealer_name(dealer_name);
            dealerModel.setDealer_class(dealer_class);
            dealerModel.setTelp_number(telp_number);
            dealerModel.setAlamat(alamat);
            dealerModel.setDealer_ext_code(dealer_ext_code);
            dealerModel.setDealer_status(dealer_status);
            dealerService.updateDealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_ext_code,dealer_status);

            dto.setDealerId(dealer_code);
            dto.setDealerName(dealer_name);
            dto.setDealerClass(dealer_class);
            dto.setTelpNumber(telp_number);
            dto.setAlamat(alamat);
            dto.setDealerExCode(dealer_ext_code);
            dto.setDealerStatus(dealer_status);

            response.put("code",201);
            response.put("data",dto);
            response.put("message","Process Successed");
            response.put("status","S");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("listAll")
    public  ResponseEntity<Object> listAll(
            @RequestBody final Map<String ,Object> request
    ){
        String  dealer_code = request.get("dealerId").toString();
        String  dealer_name = request.get("dealerName").toString().toLowerCase(Locale.ROOT);
        String  dealer_status = request.get("dealerStatus").toString().toLowerCase(Locale.ROOT);
        int offset = Integer.parseInt(request.get("offset").toString());
        int limit = Integer.parseInt(request.get("limit").toString());

        Map<String,Object> ret = new HashMap<>();
        List<DealerDTO> response = dealerService.listAll(dealer_code,dealer_status,dealer_name,offset,limit);


        DealerDTOlist dealerDTOlist= new DealerDTOlist();
        dealerDTOlist.setListDealer(response);
        dealerDTOlist.setDataOfRecord(response.size());


        ret.put("Status","S");
        ret.put("code",201);
        ret.put("message","Process Successed");
        ret.put("data",dealerDTOlist);

        return  new ResponseEntity<>(ret,HttpStatus.OK);
    }
    @GetMapping("get/{dealerId}")
        public  ResponseEntity<Object> detById (
        @PathVariable String dealerId
        ){

        List<DealerDTO> dealerDTOList = dealerService.dealerById(dealerId);
        if(dealerId.isEmpty()){
            return new ResponseEntity<>("No Data Found = 204",HttpStatus.BAD_REQUEST);
        }else {
            DealerDtoById dealerDtoById= new DealerDtoById();
            dealerDtoById.setStatus("S");
            dealerDtoById.setCode(201);
            dealerDtoById.setMessage("Process Successed");
            dealerDtoById.setData(dealerDTOList);
            return new ResponseEntity<>(dealerDtoById,HttpStatus.OK);
        }
    }
}
