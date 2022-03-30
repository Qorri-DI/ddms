package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.dto.SalesDto;
import com.alpha.ddms.models.ViewSales;
import com.alpha.ddms.repositories.ViewSalesRepository;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.*;

@RestController
@RequestMapping("/ddms/v1/cmd/master/sales")
public class SalesController {

    @Autowired
    DealerService dealerService;
    @Autowired
    SalesService salesService;
    @Autowired
    ViewSalesRepository viewSalesRepository;

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
                SalesModel data = new SalesModel();
                String latestId = salesService.findLatestId(Utils.getCurrentDateTimeString()) == null ?
                        "0" : salesService.findLatestId(Utils.getCurrentDateTimeString()).substring(13,7);
                String newId = Utils.generateLatestId(latestId);

                data.setSales_id(newId);
                data.setSales_name(salesName);

                Optional<DealerModel> dealer = dealerService.findById(dealerId);
                data.setDealerModel(dealer.get());

                SalesModel supervisor = salesService.findSupervisorId(supervisorId);
                data.setSalesModel(supervisor);

                data.setSales_gender(salesGender);
                data.setSales_email(salesEmail);
                data.setSales_status(salesStatus);

                SalesModel save = salesService.save(data);

                ResponseDto dataDto = new ResponseDto();
                dataDto.setStatus("S");
                dataDto.setCode(201);
                dataDto.setMessage("Process Success");
                dataDto.setData(save);

                return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
            }else{
                // kalau sales id diinput maka update
                Optional<SalesModel> data = salesService.findById(salesId);

                data.get().setSales_name(salesName);

                Optional<DealerModel> dealer = dealerService.findById(dealerId);
                data.get().setDealerModel(dealer.get());

                SalesModel supervisor = salesService.findSupervisorId(supervisorId);
                data.get().setSalesModel(supervisor);

                data.get().setSales_gender(salesGender);
                data.get().setSales_email(salesEmail);
                data.get().setSales_status(salesStatus);

                SalesModel save = salesService.save(data.get());
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

        Page<ViewSales> retList;
        retList = salesService.searchSales(salesName,dealerId,salesStatus,offset,limit);
        List<ViewSales> ret = retList.toList();

        ResponseDto responseDto = new ResponseDto();
        Map<String,Object> data = new LinkedHashMap<>();
        List<SalesDto> listSales = new ArrayList<>();
        SalesDto salesDto;
        for(int i = 0; i<ret.size();i++){
            salesDto = new SalesDto();
            salesDto.setSalesId(ret.get(i).getSales_id());
            salesDto.setSalesName(ret.get(i).getSales_name());
            salesDto.setDealerId(ret.get(i).getDealerModel());
            salesDto.setSupervisorId(ret.get(i).getSalesModel());
            salesDto.setSupervisorName(ret.get(i).getSales_name());
            salesDto.setSalesGender(ret.get(i).getSales_gender());
            salesDto.setSalesEmail(ret.get(i).getSales_email());
            salesDto.setSalesStatus(ret.get(i).getSales_status());
            listSales.add(salesDto);
        }
        data.put("listSales",listSales);
        data.put("dataOfRecord",retList.getTotalElements());
        if(!listSales.isEmpty()){
            responseDto.setStatus("S");
            responseDto.setCode(201);
            responseDto.setMessage("Process Success");
        }else{
            responseDto.setStatus("E");
            responseDto.setCode(204);
            responseDto.setMessage("No Data Found");
        }
        responseDto.setData(data);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value="/get/{salesId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBySalesId(
            @PathVariable(value = "salesId") final String salesId
    ){
        List errMsg;

        String strSalesId = salesId.trim();
        if(!salesId.isEmpty()){
            Optional<SalesModel> salesModel = salesService.findById(strSalesId);
            if(!salesModel.isPresent()){
                errMsg = new ArrayList();
                errMsg.add("NO DATA FOUND");
                System.out.println("masuk kesini");
                return new ResponseEntity<>(errMsg,HttpStatus.NO_CONTENT);
            }else{
                ResponseDto dataDto = new ResponseDto();
                dataDto.setStatus("S");
                dataDto.setCode(201);
                dataDto.setMessage("Process Success");
                dataDto.setData(salesModel);

                return new ResponseEntity<>(dataDto,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>("NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }
}