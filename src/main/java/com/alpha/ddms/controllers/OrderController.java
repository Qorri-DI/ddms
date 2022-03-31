package com.alpha.ddms.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ddms/v1/")
public class OrderController {

    @PostMapping("addOrder")
    public String addOrder(){
        return "";
    }
}
