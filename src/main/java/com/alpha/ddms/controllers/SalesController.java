package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ddms/v1/cmd/master/sales")
public class SalesController {

    @Autowired
    DealerService dealerService;
    @Autowired
    SalesService salesService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSales(
            @RequestBody Map<String,Object> req
    ){
        List errMsg;
        if(!req.isEmpty()) {
            String salesId = req.get("salesId") == null ? "" : req.get("salesId").toString().trim();
            String salesName = req.get("salesName") == null ? "" : req.get("salesName").toString().trim();
            String dealerId = req.get("dealerId") == null ? "" : req.get("dealerId").toString().trim();
            String supervisorId = req.get("supervisorId") == null ? "" : req.get("supervisorId").toString().trim();
            String salesGender = req.get("salesGender") == null ? "" : req.get("salesGender").toString().trim();
            String salesEmail = req.get("salesEmail") == null ? "" : req.get("salesEmail").toString().trim();
            String salesStatus = req.get("salesStatus") == null ? "" : req.get("salesStatus").toString().trim();
            if(salesId.equalsIgnoreCase("")) {
                // kalau sales ID tidak diinput create new
                SalesModel salesModel = new SalesModel();
                String latestId = salesService.findLatestId(Utils.getCurrentDateTimeString()) == null ?
                        "0" : salesService.findLatestId(Utils.getCurrentDateTimeString()).substring(13,7);
                String newId = Utils.generateLatestId(latestId);

                salesModel.setSales_id(newId);
                salesModel.setSales_name(salesName);

                DealerModel dealer = dealerService.findById(dealerId);
                salesModel.setDealerModel(dealer);

                SalesModel supervisor = salesService.findSupervisorId(supervisorId);
                salesModel.setSalesModel(salesModel);

                salesModel.setSales_gender(salesGender);
                salesModel.setSales_email(salesEmail);
                salesModel.setSales_status(salesStatus);

                SalesModel save = salesService.save(salesModel);

                return new ResponseEntity<>(save, HttpStatus.CREATED);
            }else{
                // kalau sales id diinput maka update
                SalesModel salesModel = salesService.findById(salesId);

                salesModel.setSales_name(salesName);

                DealerModel dealer = dealerService.findById(dealerId);
                salesModel.setDealerModel(dealer);

                SalesModel supervisor = salesService.findSupervisorId(supervisorId);
                salesModel.setSalesModel(salesModel);

                salesModel.setSales_gender(salesGender);
                salesModel.setSales_email(salesEmail);
                salesModel.setSales_status(salesStatus);

                SalesModel save = salesService.save(salesModel);
                return new ResponseEntity<>(save, HttpStatus.OK);
            }
        }else{
            errMsg = new ArrayList<>();
            errMsg.add("Request body not found");
            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("listAll")
    public ResponseEntity<?> listAll(
            @RequestBody Map<String,Object> req
    ){
        String salesName = req.get("salesName") == null ? "" : req.get("salesName").toString().trim();
        String dealerId = req.get("dealerId") == null ? "" : req.get("dealerId").toString().trim();
        String salesStatus = req.get("salesStatus") == null ? "" : req.get("salesStatus").toString().trim();
        int offset =  req.get("offset") == null ? 0 : Integer.valueOf(req.get("offset").toString().trim());
        int limit = req.get("limit") == null ? 0 : Integer.valueOf(req.get("limit").toString().trim());

        List<SalesModel> ret = new ArrayList<>();
        ret = salesService.searchSales(salesName,dealerId,salesStatus,offset,limit);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
