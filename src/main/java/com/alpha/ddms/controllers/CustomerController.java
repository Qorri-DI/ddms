package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
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
            return new ResponseEntity<>("dibuat", HttpStatus.CREATED);
        }else {
            customerService.updateCustomer(dto);
            return new ResponseEntity<>("updated",HttpStatus.OK);
        }
    }

    @GetMapping("/listAll")
    public List<CustomerModel> getAllCustomer(@RequestBody HashMap<String,Integer> request){
        Integer offset =  request.get("offset");
        Integer limit = request.get("limit");
        return customerService.getAllCustomer(offset,limit);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        if (!customerService.getCustomer(id).isPresent()){
            return new ResponseEntity<>("tidak ada customer dengan id " + id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerService.getCustomer(id),HttpStatus.OK);
    }


}
