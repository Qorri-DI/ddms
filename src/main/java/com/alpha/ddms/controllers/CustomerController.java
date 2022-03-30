package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ddms/v1/qry/master/customer")
public class CustomerController{
    @Autowired
    private CustomerService customerService;

    @GetMapping("/save")
    public ResponseEntity<?> postCustomer(@RequestBody CustomerRequestDto dto){
        if(dto.getCustomerId().length() < 21){
            return new ResponseEntity<>("id salah",HttpStatus.BAD_REQUEST);
        }
        if (!customerService.getCustomer(dto.getCustomerId()).isPresent()){
            customerService.saveCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"created",dto), HttpStatus.CREATED);
        }else {
            customerService.updateCustomer(dto);
            return new ResponseEntity<>(new ResponseDto<>("S",201,"updated",dto),HttpStatus.OK);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getAllCustomer(@RequestBody HashMap<String,String > request){
        String customerName = request.get("customerName");
        String dealerId = request.get("dealerId");
        Integer offset =  Integer.parseInt(request.get("offset"));
        Integer limit = Integer.parseInt(request.get("limit"));
        HashMap<String,Object> map = new HashMap<>();
        map.put("listCustomer",customerService.getAllCustomer(dealerId,customerName,offset,limit));
        map.put("dataOfRecord",customerService.getAllCustomer(dealerId,customerName,offset,limit).size());
        return new ResponseEntity<>(new ResponseDto<>("S",200,"proses successed",map),HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        if (!customerService.getCustomer(id).isPresent()){
            return new ResponseEntity<>("tidak ada customer dengan id " + id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerService.getCustomer(id),HttpStatus.OK);
    }


}
