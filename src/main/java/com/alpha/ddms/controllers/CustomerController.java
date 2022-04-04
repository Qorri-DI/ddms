package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ddms/v1/qry/master/customer")
public class CustomerController{
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> postCustomer(@RequestBody CustomerRequestDto dto){
        if (!dto.getCustomerGender().toUpperCase().equals("GTLK") ){
            if ( !dto.getCustomerGender().toUpperCase().equals("GTPR")){
                return new ResponseEntity<>("format gender salah",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("format gender salah",HttpStatus.BAD_REQUEST);
        }
        if (dto.getCustomerId() == null||dto.getCustomerId().isEmpty()){

            String id = Utils.generateLocalDateId();
            dto.setCustomerId(id);
            customerService.saveCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"created",dto), HttpStatus.CREATED);
        }else if (customerService.findById(dto.getCustomerId()).isPresent()){
            CustomerModel update = customerService.updateCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"updated",update),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getAllCustomer(@RequestBody HashMap<String,String > request){
        String customerName = request.get("customerName");
        String dealerId = request.get("dealerId");
        String off = request.get("offset");
        String limt = request.get("limit");
        if(off == null || off.isEmpty()){
            off= "0";
        }
        Integer offset =  Integer.parseInt(off);
        if (limt == null || limt.isEmpty()){
            limt = "0";
        }
        Integer limit = Integer.parseInt(limt);
        Page<CustomerModel> cm = customerService.getAllCustomer(dealerId,customerName,offset,limit);
        if(cm.getTotalElements() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("listCustomer",customerService.getAllCustomer(dealerId,customerName,offset,limit).toList());
        map.put("dataOfRecord",customerService.getAllCustomer(dealerId,customerName,offset,limit).getTotalElements());
        return new ResponseEntity<>(new ResponseDto<>("S",200,"proses successed",map),HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        if (!customerService.findById(id).isPresent()){
            return new ResponseEntity<>("tidak ada customer dengan id " + id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto<>("S",200,"success",customerService.findById(id)),HttpStatus.OK);
    }


}