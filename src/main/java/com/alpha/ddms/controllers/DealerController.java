package com.alpha.ddms.controllers;

import com.alpha.ddms.Utils.Check;
import com.alpha.ddms.Utils.GenerateJwt;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.services.DealerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
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
        String dealer_code = req.get("dealerId").toString();
        String dealer_name = req.get("dealerName").toString();
        String dealer_class = req.get("dealerClass").toString();
        String telp_number = req.get("telpNumber").toString();
        String alamat = req.get("alamat").toString();
        String dealer_ext_code = req.get("dealerExtCode").toString();
        String dealer_status = req.get("dealerStatus").toString();

        DealerDTO dto = new DealerDTO();
        DealerModel dealerModel = new DealerModel();
        Optional<DealerModel> dealerCode = dealerService.findByDealerCode(dealer_code);

        if (req.isEmpty()) {
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

            DealerDtoPost dp = new DealerDtoPost();
            dp.setCode(201);
            dp.setData(dtoList);
            dp.setMassage("Process Successed");
            dp.setStatus("S");

            return new ResponseEntity<>(dp, HttpStatus.OK);

        } else {
            dealerModel.setDealer_code(dealer_code);
            if(Check.isNullOrEmpty(dealer_name)){
                dealerModel.setDealer_name(dealerCode.get().getDealer_name());
            }else {
                dealerModel.setDealer_name(dealer_name);
            }
            if (Check.isNullOrEmpty(dealer_class)){
                dealerModel.setDealer_class(dealerCode.get().getDealer_class());
            }else {
                dealerModel.setDealer_class(dealer_class);
            }
            if (Check.isNullOrEmpty(telp_number)){
                dealerModel.setTelp_number(dealerCode.get().getTelp_number());
            }else {
                dealerModel.setTelp_number(telp_number);
            }
            if (Check.isNullOrEmpty(alamat)){
                dealerModel.setAlamat(dealerCode.get().getAlamat());
            }else {
                dealerModel.setAlamat(alamat);
            }
            if (Check.isNullOrEmpty(dealer_ext_code)){
                dealerModel.setDealer_ext_code(dealerCode.get().getDealer_ext_code());
            }else {
                dealerModel.setDealer_ext_code(dealer_ext_code);
            }
            if (Check.isNullOrEmpty(dealer_status)){
                dealerModel.setDealer_status(dealerCode.get().getDealer_status());
            }else {
                dealerModel.setDealer_status(dealer_status);
            }
            dealerService.save(dealerModel);

            List<DealerDTO> dtoList = new ArrayList<>();
            dto.setDealerId(dealer_code);
            dto.setDealerName(dealerCode.get().getDealer_name());
            dto.setDealerClass(dealerCode.get().getDealer_class());
            dto.setTelpNumber(dealerCode.get().getTelp_number());
            dto.setAlamat(dealerCode.get().getAlamat());
            dto.setDealerExCode(dealerCode.get().getDealer_ext_code());
            dto.setDealerStatus(dealerCode.get().getDealer_status());
            dtoList.add(dto);

            DealerDtoPost dt = new DealerDtoPost();
            dt.setCode(201);
            dt.setData(dtoList);
            dt.setMassage("Process Successed");
            dt.setStatus("S");
            return new ResponseEntity<>(dt, HttpStatus.OK);
        }
    }

    @PostMapping("listAll")
    public ResponseEntity<Object> listAll(
            @RequestBody final Map<String, Object> request
    ) {
        String dealer_code = request.get("dealerId").toString();
        String dealer_name = request.get("dealerName").toString().toLowerCase(Locale.ROOT);
        String dealer_status = request.get("dealerStatus").toString().toLowerCase(Locale.ROOT);
        int offset = Integer.parseInt(request.get("offset").toString());
        int limit = Integer.parseInt(request.get("limit").toString());

        List<DealerDTO> response = dealerService.listAll(dealer_code, dealer_status, dealer_name, offset, limit);

        DealerDTOlist dtl = new DealerDTOlist();
        dtl.setListDealer(response);
        dtl.setDataOfRecord(response.size());

        DealerDtoListAll dl=new DealerDtoListAll();
        dl.setStatus("S");
        dl.setCode(201);
        dl.setMessage("Process Successed");
        dl.setData(dtl);
        return new ResponseEntity<>(dl, HttpStatus.OK);
    }
    @GetMapping(value = "get/{dealerId}")
    public ResponseEntity<Object> detById(
            @PathVariable String dealerId
    ) {
        DealerDTO dealerDTOList = new DealerDTO();
        try {
            dealerDTOList = dealerService.dealerById(dealerId);
            Optional<DealerModel> dealerCode = dealerService.findByGetId(dealerId);
            if (!Check.isNullOrEmpty(dealerId)) {
                DealerDtoById dealerDtoById = new DealerDtoById();
                dealerDtoById.setStatus("S");
                dealerDtoById.setCode(201);
                dealerDtoById.setMessage("Process Successed");
                dealerDtoById.setData(dealerDTOList);
                return new ResponseEntity<>(dealerDtoById, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>("data tidak ada", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("createJwt")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody final Map<String, Object> request
    ) {
        Map<String, Object> ret = new HashMap<>();
        String userId = request.get("userId").toString();
        String dealer_code = request.get("dealerId").toString();

        Optional<DealerModel> cek = dealerService.findByDealerCode(dealer_code);

        if (!cek.isPresent()) {
            ret.put("status", "DealerId tidak terdaftar");
        } else {
            String token = GenerateJwt.createToken(userId + dealer_code);
            ret.put("token", token);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}

