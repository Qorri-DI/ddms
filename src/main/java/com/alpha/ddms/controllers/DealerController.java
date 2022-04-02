package com.alpha.ddms.controllers;

import com.alpha.ddms.common.*;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.DealerService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;

    @PostMapping("save")
    public ResponseEntity<Object> saveDealer(
            @RequestBody final Map<String, Object> req,
            @RequestHeader(required = false, value ="token") String token,
            @RequestHeader(required = false, value ="userId") String userId
    ) {
        String dealer_code = req.get("dealerId").toString();
        String dealer_name = req.get("dealerName").toString();
        String dealer_class = req.get("dealerClass").toString();
        String telp_number = req.get("telpNumber").toString();
        String alamat = req.get("alamat").toString();
        String dealer_ext_code = req.get("dealerExtCode").toString();
        String dealer_status = req.get("dealerStatus").toString();

        Claims claims = JWTGenerate.validToken(token);
        if(userId.equals(claims.getId())) {
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
                if (Checks.isNullOrEmpty(dealer_name)) {
                    dealerModel.setDealer_name(dealerCode.get().getDealer_name());
                } else {
                    dealerModel.setDealer_name(dealer_name);
                }
                if (Checks.isNullOrEmpty(dealer_class)) {
                    dealerModel.setDealer_class(dealerCode.get().getDealer_class());
                } else {
                    dealerModel.setDealer_class(dealer_class);
                }
                if (Checks.isNullOrEmpty(telp_number)) {
                    dealerModel.setTelp_number(dealerCode.get().getTelp_number());
                } else {
                    dealerModel.setTelp_number(telp_number);
                }
                if (Checks.isNullOrEmpty(alamat)) {
                    dealerModel.setAlamat(dealerCode.get().getAlamat());
                } else {
                    dealerModel.setAlamat(alamat);
                }
                if (Checks.isNullOrEmpty(dealer_ext_code)) {
                    dealerModel.setDealer_ext_code(dealerCode.get().getDealer_ext_code());
                } else {
                    dealerModel.setDealer_ext_code(dealer_ext_code);
                }
                if (Checks.isNullOrEmpty(dealer_status)) {
                    dealerModel.setDealer_status(dealerCode.get().getDealer_status());
                } else {
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
        }else{
            return new ResponseEntity<>("useId atau token salah",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("createJwt")
    public ResponseEntity<Map<String, Object>> login(
            @RequestHeader("userId")String userId,
            @RequestBody final Map<String, Object> request
    ) {
        Map<String, Object> ret = new HashMap<>();
        String dealer_code = request.get("dealerId").toString();

        Optional<DealerModel> cek = dealerService.findByDealerCode(dealer_code);

        if (!cek.isPresent()) {
            ret.put("status", "DealerId tidak terdaftar");
        } else {
            String token = JWTGenerate.createToken(userId);
            ret.put("token", token);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
