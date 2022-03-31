package com.alpha.ddms.controllers;

import com.alpha.ddms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController{
    @Autowired
    private CustomerService service;
}
