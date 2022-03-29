package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.dto.CustomerRequestDto;
import com.alpha.ddms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ddms/v1/qry/master/customer")
public class CustomerController{
    @Autowired
    private CustomerService service;

    @GetMapping("/save")
    public ResponseEntity<?> postCustomer(CustomerRequestDto dto){
        if (service.getCustomer(dto.getCustomerId()) == null){
            return new ResponseEntity<>("kosong", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("berhasil",HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public List<CustomerModel> getAllCustomer(@RequestBody HashMap<String,Integer> request){
        Integer offset =  request.get("offset");
        Integer limit = request.get("limit");
        return service.getAllCustomer(offset,limit);
    }


}
