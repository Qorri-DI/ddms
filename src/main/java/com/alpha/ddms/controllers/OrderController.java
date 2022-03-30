package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ddms/v1/cmd/transaction/order")
public class OrderController {

    @Autowired OrderService orderService;
    @Autowired UnitService unitService;
    @Autowired DealerService dealerService;
    @Autowired SalesService salesService;
    @Autowired CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody Map<String,Object> req
    ){
        List errMsg;

        if(!req.isEmpty()) {
            String orderId = req.get("orderId") == null ? "" : req.get("orderId").toString().trim();
            String unitCode = req.get("unitCode") == null ? "" : req.get("unitCode").toString().trim();
            String dealerCode = req.get("dealerCode") == null ? "" : req.get("dealerCode").toString().trim();
            String salesId = req.get("salesId") == null ? "" : req.get("salesId").toString().trim();
            String customerId = req.get("customerId") == null ? "" : req.get("customerId").toString().trim();
            float minimumPayment = req.get("minimumPayment") == null ? null : Float.parseFloat(req.get("minimumPayment").toString().trim());
            float totalValue = req.get("totalValue") == null ? null : Float.parseFloat(req.get("totalValue").toString().trim());
            float orderValue = req.get("orderValue") == null ? null : Float.parseFloat(req.get("orderValue").toString().trim());
            float offtheroadValue = req.get("offtheroadValue") == null ? null : Float.parseFloat(req.get("offtheroadValue").toString().trim());
            float orderDiscount = req.get("orderDiscount") == null ? null : Float.parseFloat(req.get("orderDiscount").toString().trim());
            float ppn = req.get("ppn") == null ? null : Float.parseFloat(req.get("ppn").toString().trim());
            String platNomor = req.get("platNomor") == null ? "" : req.get("platNomor").toString().trim();
            String nomorMesin = req.get("nomorMesin") == null ? "" : req.get("nomorMesin").toString().trim();
            String nomorRangka = req.get("nomorRangka") == null ? "" : req.get("nomorRangka").toString().trim();
            String isLeasing = req.get("isLeasing") == null ? "" : req.get("isLeasing").toString().trim();
            String paymentStatus = req.get("paymentStatus") == null ? "" : req.get("paymentStatus").toString().trim();
            String unitStatus = req.get("unitStatus") == null ? "" : req.get("unitStatus").toString().trim();

            if(orderId.equalsIgnoreCase("")){
                // kalau sales ID tidak diinput create new
                OrderModel data = new OrderModel();
                String latestId = orderService.findLatestId(Utils.getCurrentDateTimeString()) == null ?
                        "0" : orderService.findLatestId(Utils.getCurrentDateTimeString()).substring(13,7);
                String newId = Utils.generateLatestId(latestId);

                data.setOrder_id(newId);

                Optional<UnitModel> unit = unitService.findByIdunit(unitCode);
                data.setUnitModel(unit.get());

                Optional<DealerModel> dealer = dealerService.findById(dealerCode);
                data.setDealerModel(dealer.get());

                Optional<SalesModel> sales = salesService.findById(salesId);
                data.setSalesModel(sales.get());

                Optional<CustomerModel> customer = customerService.findById(customerId);
                data.setCustomerModel(customer.get());

                data.setMinimum_payment(minimumPayment);
                data.setTotal_value(totalValue);
                data.setOrder_value(orderValue);
                data.setOfftheroad_value(offtheroadValue);
                data.setOrder_total_discount(orderDiscount);
                data.setPpn(ppn);
                data.setPlat_nomor(platNomor);
                data.setNomor_mesin(nomorMesin);
                data.setNomor_rangka(nomorRangka);
                data.setIs_leasing(isLeasing);
                data.setPayment_status(paymentStatus);
                data.setUnit_status(unitStatus);

                OrderModel save = orderService.save(data);
                return new ResponseEntity<>(save,HttpStatus.CREATED);
            }else{
                Optional<OrderModel> data = orderService.findById(orderId);

                Optional<UnitModel> unit = unitService.findByIdunit(unitCode);
                data.get().setUnitModel(unit.get());

                Optional<DealerModel> dealer = dealerService.findById(dealerCode);
                data.get().setDealerModel(dealer.get());

                Optional<SalesModel> sales = salesService.findById(salesId);
                data.get().setSalesModel(sales.get());

                Optional<CustomerModel> customer = customerService.findById(customerId);
                data.get().setCustomerModel(customer.get());
                data.get().setMinimum_payment(minimumPayment);
                data.get().setTotal_value(totalValue);
                data.get().setOrder_value(orderValue);
                data.get().setOfftheroad_value(offtheroadValue);
                data.get().setOrder_total_discount(orderDiscount);
                data.get().setPpn(ppn);
                data.get().setPlat_nomor(platNomor);
                data.get().setNomor_mesin(nomorMesin);
                data.get().setNomor_rangka(nomorRangka);
                data.get().setIs_leasing(isLeasing);
                data.get().setPayment_status(paymentStatus);
                data.get().setUnit_status(unitStatus);

                OrderModel save = orderService.save(data.get());
                return new ResponseEntity<>(save,HttpStatus.OK);
            }
        }else{
            errMsg = new ArrayList<>();
            errMsg.add("Request body not found");
            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
        }
    }
}