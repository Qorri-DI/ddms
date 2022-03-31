package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.services.CustomerService;
import org.graalvm.compiler.core.common.util.Util;
import org.hibernate.validator.cfg.defs.EmailDef;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;

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
        if(dto.getCustomerEmail().isEmpty()){
            return new ResponseEntity<>("Email kosong",HttpStatus.BAD_REQUEST);
        }
        if (dto.getCustomerNik().length() < 16){
            return new ResponseEntity<>("format NIK salah",HttpStatus.BAD_REQUEST);
        }
        if (dto.getCustomerId().isEmpty() || dto.getCustomerId() == null){

            String id = Utils.generateLocalDateId();
            dto.setCustomerId(id);
            customerService.saveCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"created",dto), HttpStatus.CREATED);


        }else if (customerService.findById(dto.getCustomerId()).isPresent()){
            customerService.updateCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"updated",dto),HttpStatus.OK);
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
        }else if (Utils.checkId(id)){
            return new ResponseEntity<>("fromat id salah",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerService.findById(id),HttpStatus.OK);
    }


}
