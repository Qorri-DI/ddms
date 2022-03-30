package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.OrderModel;
import com.alpha.ddms.dto.AllOrderRequestDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("ddms/v1/qry/transaction/order/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/listAll")
    public ResponseEntity<?> getAllOrder(@RequestBody AllOrderRequestDto dto){
        List<OrderModel> orderModels = orderService.getAllOrder(dto.getDealerId(),
                dto.getPlatNomor(),
                dto.getNomorMesin(),
                dto.getNomorRangka(),
                dto.getPaymentStatus(),
                dto.getOffset(),
                dto.getLimit());

        HashMap<String,Object> map = new HashMap<>();
        map.put("listOrder",orderModels);
        map.put("dataOfRecord",orderModels.size());
        return new ResponseEntity<>(new ResponseDto<>("S",200,"success",map),HttpStatus.OK);
    }
}
