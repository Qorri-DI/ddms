package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;

    @PostMapping("save")
    public ResponseEntity<Object> saveDealer(
            @RequestBody final Map<String, Object> req
    ) {
        List response = new ArrayList<>();
        String dealer_code = req.get("dealerId").toString();
        String dealer_name = req.get("dealerName").toString();
        String dealer_class = req.get("dealerClass").toString();
        String telp_number = req.get("telpNumber").toString();
        String alamat = req.get("alamat").toString();
        String dealer_ext_code = req.get("dealerExtCode").toString();
        String dealer_status = req.get("dealerStatus").toString();

        Optional<DealerModel> dealerCode = dealerService.findByCode(dealer_code);
        Optional<DealerModel> dealerName = dealerService.findByName(dealer_name);

        if (!dealerCode.isPresent() || !dealerName.isPresent()) {
            int dealerModel = dealerService.saveDealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code);

            return new ResponseEntity<>(dealerModel, HttpStatus.OK);

        } else {
            int updateDealer = dealerService.updateDealer(dealer_code, dealer_name);
            response.add(updateDealer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
