package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Checks;
import com.alpha.ddms.common.JWTGenerate;
import com.alpha.ddms.common.Utils;
import com.alpha.ddms.configuration.ConfigProperties;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.AllOrderRequestDto;
import com.alpha.ddms.dto.OrderSaveDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@RestController
@RequestMapping("/ddms/v1")
public class OrderController {

    @Autowired OrderService orderService;
    @Autowired UnitService unitService;
    @Autowired DealerService dealerService;
    @Autowired SalesService salesService;
    @Autowired CustomerService customerService;

    List ret;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<List> handleMissingParam(MissingServletRequestParameterException e){
        String name = e.getParameterName();
        ret = new ArrayList();
        ret.add(name + " param not found");
        return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<List> handleMissingRequestHeader(MissingRequestHeaderException e){
        String name = e.getHeaderName();
        ret = new ArrayList();
        ret.add(name + " param not found");
        return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List> handleMissingRequestHeader(HttpMessageNotReadableException e){
        String name[] = e.getLocalizedMessage().split(":",2);
        ret = new ArrayList();
        ret.add(name[0]);
        return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<List> handleNullPointerException(NullPointerException e){
        String name = e.getMessage();
        ret = new ArrayList();
        ret.add(name);
        return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<List> handleNumberFormatException(NumberFormatException e){
        String name[] = e.getLocalizedMessage().split(":",2);
        ret = new ArrayList<>();
        String tampung = name[1].replace("\"","");
        ret.add("Invalid number : " + tampung);
        return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cmd/transaction/order/save")
    public ResponseEntity<?> save(
            @RequestBody Map<String,Object> req,
            @RequestHeader( value = "userId") String userId,
            @RequestHeader( value = "dealerId") String dealerId,
            @RequestHeader( value = "token") String token
    ){
        List errMsg;
        boolean isValid = false;
        boolean allowUpdate = false;

        try {
            if(Checks.isNullOrEmpty(userId)){
                ret = new ArrayList<>();
                ret.add("JWT Fail : User Id kosong");
                return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
            }else if(Checks.isNullOrEmpty(dealerId)){
                ret = new ArrayList<>();
                ret.add("JWT Fail : DealerId Kosong");
                return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
            }else if(Checks.isNullOrEmpty(token)){
                ret = new ArrayList();
                ret.add("JWT Fail : Token kosong");
                return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
            }
            Claims claims = JWTGenerate.validToken(token);
            if(!claims.getId().equals(userId+dealerId)){
                ret = new ArrayList<>();
                ret.add("JWT Fail : Invalid token for userId : " + userId + " dealerId : " + dealerId);
                return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
            }else{
//                System.out.println("JWT SUCCESS");
        if(!req.isEmpty()) {
            String orderId = req.get("orderId") == null ? "" : req.get("orderId").toString().trim();
            String unitCode = req.get("unitCode") == null ? "" : req.get("unitCode").toString().trim();
            String dealerCode = req.get("dealerCode") == null ? "" : req.get("dealerCode").toString().trim();
            String salesId = req.get("salesId") == null ? "" : req.get("salesId").toString().trim();
            String customerId = req.get("customerId") == null ? "" : req.get("customerId").toString().trim();
            float minimumPayment = req.get("minimumPayment") == null ? 0 : Float.parseFloat(req.get("minimumPayment").toString().trim());
            float totalValue = req.get("totalValue") == null ? 0 : Float.parseFloat(req.get("totalValue").toString().trim());
            float orderValue = req.get("orderValue") == null ? 0 : Float.parseFloat(req.get("orderValue").toString().trim());
            float offtheroadValue = req.get("offtheroadValue") == null ? 0 : Float.parseFloat(req.get("offtheroadValue").toString().trim());
            float orderDiscount = req.get("orderDiscount") == null ? 0 : Float.parseFloat(req.get("orderDiscount").toString().trim());
            float ppn = req.get("ppn") == null ? 0 : Float.parseFloat(req.get("ppn").toString().trim());
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
                if(!unitCode.equalsIgnoreCase("")){
                    Optional<UnitModel> unit = unitService.findByIdunit(unitCode);
                    if(unit.isPresent()){
                       data.setUnitModel(unit.get());
                       isValid = true;
                    }else{
                        errMsg = new ArrayList<>();
                        errMsg.add("invalid unit");
                        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                    }
                }else{
                    errMsg = new ArrayList<>();
                    errMsg.add("unit code tidak boleh kosong");
                    return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                }

                if(!dealerCode.equalsIgnoreCase("")) {
                    Optional<DealerModel> dealer = dealerService.findById(dealerCode);
                    if(dealer.isPresent()) {
                        data.setDealerModel(dealer.get());
                        isValid = true;
                    }else{
                        errMsg = new ArrayList<>();
                        errMsg.add("invalid dealer");
                        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                    }
                }else{
                    errMsg = new ArrayList<>();
                    errMsg.add("dealer code tidak boleh kosong");
                    return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                }

                if(!salesId.equalsIgnoreCase("")) {
                    Optional<SalesModel> sales = salesService.findById(salesId);
                    if(sales.isPresent()){
                        data.setSalesModel(sales.get());
                        isValid = true;
                    }else{
                        errMsg = new ArrayList<>();
                        errMsg.add("invalid sales");
                        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                    }
                }else{
                    errMsg = new ArrayList<>();
                    errMsg.add("sales id tidak boleh kosong");
                    return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                }

                if(!customerId.equalsIgnoreCase("")) {
                    Optional<CustomerModel> customer = customerService.findById(customerId);
                    if(customer.isPresent()) {
                        data.setCustomerModel(customer.get());
                        isValid = true;
                    }else{
                        errMsg = new ArrayList<>();
                        errMsg.add("invalid customer");
                        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                    }
                }else{
                    errMsg = new ArrayList<>();
                    errMsg.add("customer id tidak boleh kosong");
                    return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                }

                data.setMinimum_payment(minimumPayment);

                if(totalValue != 0) {
                    data.setTotal_value(totalValue);
                    isValid = true;
                }else{
                    totalValue = orderValue - orderDiscount;
                    data.setTotal_value(totalValue);
                    isValid = true;
                }

                data.setOrder_value(orderValue);
                data.setOfftheroad_value(offtheroadValue);
                data.setOrder_total_discount(orderDiscount);

                if(ppn != 0){
                    data.setPpn(ppn);
                    isValid = true;
                }else{
                    //float ppnRateEffective = ppnService.getEffectivePpn(LocalDateTime.now());
                    //float hasilPerhitunganPpn = hitungPpn(offtheroadValue, orderDiscount, ppnRateEffective);
                    float hasilPerhitunganPpn = ppn;
                    data.setPpn(hasilPerhitunganPpn);
                }

                if(!platNomor.equalsIgnoreCase("")){
                    data.setPlat_nomor(platNomor);
                    isValid = true;
                }else{
                    data.setPlat_nomor(null);
                    isValid = true;
                }

                if(!nomorMesin.equalsIgnoreCase("")){
                    data.setNomor_mesin(nomorMesin);
                    isValid = true;
                }else{
                    data.setNomor_mesin(null);
                    isValid = true;
                }

                if(!nomorMesin.equalsIgnoreCase("")){
                    data.setNomor_rangka(nomorRangka);
                    isValid = true;
                }else{
                    data.setNomor_rangka(null);
                    isValid = true;
                }

                if(isLeasing.equalsIgnoreCase("YES")){
                    if(paymentStatus.matches("^\\d+$")){
                        if(Arrays.stream(Checks.PAYMENT_STATUS_ENUM)
                                .anyMatch(Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1]::equalsIgnoreCase)){
                            if((Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1])
                                    .equalsIgnoreCase("PARTIAL_PAID")){
                                data.setIs_leasing(isLeasing);
                                data.setPayment_status(Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1]);
                                isValid = true;
                            }else{
                                errMsg = new ArrayList<>();
                                errMsg.add("jika leasing, harus membayar sebagian(partial_paid)");
                                return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                            }
                        }else{
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid payment status");
                            return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                        }
                    }else{
                        if(Arrays.stream(Checks.PAYMENT_STATUS_ENUM)
                                .anyMatch(paymentStatus::equalsIgnoreCase)){
                            if(paymentStatus.equalsIgnoreCase("PARTIAL_PAID")){
                                data.setIs_leasing(isLeasing);
                                data.setPayment_status(paymentStatus);
                                isValid = true;
                            }else{
                                errMsg = new ArrayList<>();
                                errMsg.add("jika leasing, harus membayar sebagian(partial_paid)");
                                return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                            }
                        }else{
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid payment status");
                            return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                        }
                    }
                }else{
                    if(paymentStatus.matches("^\\d+$")){
                        if(Arrays.stream(Checks.PAYMENT_STATUS_ENUM)
                                .anyMatch(Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1]::equalsIgnoreCase)){
                            if((Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1])
                                    .equalsIgnoreCase("FULLY_PAID")){
                                data.setIs_leasing(isLeasing);
                                data.setPayment_status(Checks.PAYMENT_STATUS_ENUM[Integer.parseInt(paymentStatus)-1]);
                                isValid = true;
                            }else{
                                errMsg = new ArrayList<>();
                                errMsg.add("jika tidak leasing, harus membayar full payment(fully_paid)");
                                return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                            }
                        }else{
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid payment status");
                            return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                        }
                    }else{
                        if(Arrays.stream(Checks.PAYMENT_STATUS_ENUM)
                                .anyMatch(paymentStatus::equalsIgnoreCase)){
                            if(paymentStatus.equalsIgnoreCase("PARTIAL_PAID")){
                                errMsg = new ArrayList();
                                errMsg.add("jika tidak leasing, harus full payment (fully_paid)");
                                return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                            }else{
                                data.setIs_leasing(isLeasing);
                                data.setPayment_status(paymentStatus);
                                isValid = true;
                            }
                        }else{
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid payment status");
                            return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                        }
                    }
                }
                if(!unitStatus.equalsIgnoreCase("")){
                    if (Arrays.stream(Checks.UNIT_STATUS_ENUM)
                            .anyMatch(unitStatus::equalsIgnoreCase)) {
                        data.setUnit_status(unitStatus);
                        isValid = true;
                    }else if(unitStatus.matches("^\\d+$")){
                        if(Arrays.stream(Checks.UNIT_STATUS_ENUM)
                                .anyMatch(Checks.UNIT_STATUS_ENUM[Integer.valueOf(unitStatus.toString())-1]
                                        ::equalsIgnoreCase)){
                            data.setUnit_status(Checks.UNIT_STATUS_ENUM[Integer.valueOf(unitStatus.toString())-1]);
                            isValid = true;
                        }
                    }else{
                        errMsg = new ArrayList();
                        errMsg.add("invalid unit status");
                        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                    }
                }else {
                    errMsg = new ArrayList();
                    errMsg.add("harap masukkan unit status");
                    return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);
                }

                if(isValid){
                    OrderModel save = orderService.save(data);
                    OrderSaveDto orderSaveDTO = new OrderSaveDto();

                    orderSaveDTO.setOrderId(save.getOrder_id());
                    orderSaveDTO.setUnitCode(save.getUnitModel().getUnit_id());
                    orderSaveDTO.setDealerCode(save.getDealerModel().getDealer_code());
                    orderSaveDTO.setSalesId(save.getSalesModel().getSales_id());
                    orderSaveDTO.setCustomerId(save.getCustomerModel().getCustomer_id());
                    orderSaveDTO.setMinimumPayment(String.format("%.0f",save.getMinimum_payment()));
                    orderSaveDTO.setTotalValue(String.format("%.0f",save.getTotal_value()));
                    orderSaveDTO.setOrderValue(String.format("%.0f",save.getOrder_value()));
                    orderSaveDTO.setOfftheroadValue(String.format("%.0f",save.getOfftheroad_value()));
                    orderSaveDTO.setOrderDiscount(String.format("%.0f",save.getOrder_total_discount()));
                    orderSaveDTO.setPpn(String.format("%.0f",save.getPpn()));
                    orderSaveDTO.setPlatNomor(save.getPlat_nomor());
                    orderSaveDTO.setNomorMesin(save.getNomor_mesin());
                    orderSaveDTO.setNomorRangka(save.getNomor_rangka());
                    orderSaveDTO.setIsLeasing(save.getIs_leasing());
                    orderSaveDTO.setPaymentStatus(save.getPayment_status());
                    orderSaveDTO.setUnitStatus(save.getUnit_status());

                    ResponseDto responseDto = new ResponseDto<>();
                    responseDto.setStatus("S");
                    responseDto.setCode(201);
                    responseDto.setMessage("Process Success");
                    responseDto.setData(orderSaveDTO);
                    return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>("ERROR BAD REQUEST", HttpStatus.BAD_REQUEST);
                }
            }else {
                Optional<OrderModel> data = orderService.findById(orderId);
                if (data.isPresent()) {
                    if (!unitCode.equalsIgnoreCase("")) {
                        Optional<UnitModel> unit = unitService.findByIdunit(unitCode);
                        if (unit.isPresent()) {
                            data.get().setUnitModel(unit.get());
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid unit");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setUnitModel(data.get().getUnitModel());
                        allowUpdate = true;
                    }

                    if (!dealerCode.equalsIgnoreCase("")) {
                        Optional<DealerModel> dealer = dealerService.findById(dealerCode);
                        if (dealer.isPresent()) {
                            data.get().setDealerModel(dealer.get());
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid dealer");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setDealerModel(data.get().getDealerModel());
                        allowUpdate = true;
                    }

                    if (!salesId.equalsIgnoreCase("")) {
                        Optional<SalesModel> sales = salesService.findById(salesId);
                        if (sales.isPresent()) {
                            data.get().setSalesModel(sales.get());
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid sales");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setSalesModel(data.get().getSalesModel());
                        allowUpdate = true;
                    }

                    if (!customerId.equalsIgnoreCase("")) {
                        Optional<CustomerModel> customer = customerService.findById(customerId);
                        if (customer.isPresent()) {
                            data.get().setCustomerModel(customer.get());
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList();
                            errMsg.add("invalid customer");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setCustomerModel(data.get().getCustomerModel());
                        allowUpdate = true;
                    }

                    if (minimumPayment != 0) {
                        data.get().setMinimum_payment(minimumPayment);
                        allowUpdate = true;
                    } else {
                        data.get().setMinimum_payment(data.get().getMinimum_payment());
                        allowUpdate = true;
                    }

                    if (totalValue != 0) {
                        data.get().setTotal_value(totalValue);
                        allowUpdate = true;
                    } else {
                        data.get().setTotal_value(data.get().getTotal_value());
                        allowUpdate = true;
                    }

                    if (orderValue != 0) {
                        data.get().setOrder_value(orderValue);
                        allowUpdate = true;
                    } else {
                        data.get().setOrder_value(data.get().getOrder_value());
                        allowUpdate = true;
                    }

                    if (offtheroadValue != 0) {
                        data.get().setOfftheroad_value(offtheroadValue);
                        allowUpdate = true;
                    } else {
                        data.get().setOfftheroad_value(data.get().getOfftheroad_value());
                        allowUpdate = true;
                    }

                    if (orderDiscount != 0) {
                        data.get().setOrder_total_discount(orderDiscount);
                        allowUpdate = true;
                    } else {
                        data.get().setOrder_total_discount(data.get().getOrder_total_discount());
                        allowUpdate = true;
                    }

                    if (ppn != 0) {
                        data.get().setPpn(ppn);
                        allowUpdate = true;
                    } else {
                        data.get().setPpn(data.get().getPpn());
                        allowUpdate = true;
                    }

                    if (!platNomor.equalsIgnoreCase("")) {
                        data.get().setPlat_nomor(platNomor);
                        allowUpdate = true;
                    } else {
                        data.get().setPlat_nomor(data.get().getPlat_nomor());
                        allowUpdate = true;
                    }

                    if (!nomorMesin.equalsIgnoreCase("")) {
                        data.get().setNomor_mesin(nomorMesin);
                        allowUpdate = true;
                    } else {
                        data.get().setNomor_mesin(data.get().getNomor_mesin());
                        allowUpdate = true;
                    }

                    if (!nomorRangka.equalsIgnoreCase("")) {
                        data.get().setNomor_rangka(nomorRangka);
                        allowUpdate = true;
                    } else {
                        data.get().setNomor_rangka(data.get().getNomor_rangka());
                        allowUpdate = true;
                    }

                    if (!isLeasing.equalsIgnoreCase("")) {
                        if (isLeasing.equalsIgnoreCase("YES") || isLeasing.equalsIgnoreCase("NO")) {
                            data.get().setIs_leasing(isLeasing);
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList();
                            errMsg.add("invalid leasing status: YES/NO");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setIs_leasing(data.get().getIs_leasing());
                        allowUpdate = true;
                    }

                    if (!paymentStatus.equalsIgnoreCase("")) {
                        if (Checks.validPaymentStatus(paymentStatus)) {
                            data.get().setPayment_status(paymentStatus);
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid payment status");
                            allowUpdate = false;
                        }
                    } else {
                        data.get().setPayment_status(data.get().getPayment_status());
                        allowUpdate = true;
                    }

                    if (!unitStatus.equalsIgnoreCase("")) {
                        if (Checks.validUnitStatus(unitStatus)) {
                            data.get().setUnit_status(unitStatus);
                            allowUpdate = true;
                        } else {
                            errMsg = new ArrayList<>();
                            errMsg.add("invalid unit status code");
                            allowUpdate = false;
                            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        data.get().setUnit_status(data.get().getUnit_status());
                        allowUpdate = true;
                    }

                    if (allowUpdate) {
                        OrderModel save = orderService.save(data.get());
                        OrderSaveDto orderSaveDTO = new OrderSaveDto();
                        NumberFormat nf = NumberFormat.getInstance();

                        orderSaveDTO.setOrderId(save.getOrder_id());
                        orderSaveDTO.setUnitCode(save.getUnitModel().getUnit_id());
                        orderSaveDTO.setDealerCode(save.getDealerModel().getDealer_code());
                        orderSaveDTO.setSalesId(save.getSalesModel().getSales_id());
                        orderSaveDTO.setCustomerId(save.getCustomerModel().getCustomer_id());
                        orderSaveDTO.setMinimumPayment(String.format("%.0f",save.getMinimum_payment()));
                        orderSaveDTO.setTotalValue(String.format("%.0f",save.getTotal_value()));
                        orderSaveDTO.setOrderValue(String.format("%.0f",save.getOrder_value()));
                        orderSaveDTO.setOfftheroadValue(String.format("%.0f",save.getOfftheroad_value()));
                        orderSaveDTO.setOrderDiscount(String.format("%.0f",save.getOrder_total_discount()));
                        orderSaveDTO.setPpn(String.format("%.0f",save.getPpn()));
                        orderSaveDTO.setPlatNomor(save.getPlat_nomor());
                        orderSaveDTO.setNomorMesin(save.getNomor_mesin());
                        orderSaveDTO.setNomorRangka(save.getNomor_rangka());
                        orderSaveDTO.setIsLeasing(save.getIs_leasing());
                        orderSaveDTO.setPaymentStatus(save.getPayment_status());
                        orderSaveDTO.setUnitStatus(save.getUnit_status());

                        ResponseDto responseDto = new ResponseDto<>();
                        responseDto.setStatus("S");
                        responseDto.setCode(201);
                        responseDto.setMessage("Process Success");
                        responseDto.setData(orderSaveDTO);
                        return new ResponseEntity<>(responseDto, HttpStatus.OK);
                    } else {
                        errMsg = new ArrayList<>();
                        errMsg.add("invalid request");
                        return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    errMsg = new ArrayList<>();
                    errMsg.add("no data found");
                    return new ResponseEntity<>(errMsg, HttpStatus.NO_CONTENT);
                }
            }
        }else{
            errMsg = new ArrayList<>();
            errMsg.add("Request body not found");
            return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
        }
        }
        }catch (ExpiredJwtException e){
            ret = new ArrayList<>();
            ret.add("JWT Expired");
            return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
        }catch (SignatureException e){
            ret = new ArrayList<>();
            ret.add("JWT Failed : Signature");
            return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ret = new ArrayList<>();
            ret.add("JWT Failed : Others");
            return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/qry/transaction/order/listAll")
    public ResponseEntity<?> getAllOrder(@RequestBody AllOrderRequestDto dto){
        if(dto.getDealerId().isEmpty()){
            return new ResponseEntity<>("tolong masukkan dealer ID",HttpStatus.BAD_REQUEST);
        }
        if(dto.getLimit() == 0 || dto.getLimit() == null){
            dto.setLimit(ConfigProperties.getConstant_max_limit());
        }
        if( dto.getOffset() == null){
            dto.setOffset(0);
        }
        Page<OrderModel> orderModels = orderService.getAllOrder(dto.getDealerId(),
                dto.getPlatNomor(),
                dto.getNomorMesin(),
                dto.getNomorRangka(),
                dto.getPaymentStatus(),
                dto.getOffset(),
                dto.getLimit());

        if (orderModels.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("listOrder",orderModels);
        map.put("dataOfRecord",orderModels.getTotalElements());
        return new ResponseEntity<>(new ResponseDto<>("S",200,"success",map),HttpStatus.OK);
    }

    @GetMapping("/qry/transaction/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") String id){
//        if(Utils.checkId(id) == false){
//            return new ResponseEntity<>("format id salah",HttpStatus.BAD_REQUEST);
//        }
        if (orderService.findById(id).isPresent()){
            return new ResponseEntity<>(orderService.findById(id),HttpStatus.OK) ;
        }else {
            return new ResponseEntity<>("data kosong", HttpStatus.NO_CONTENT);
        }

    }


}