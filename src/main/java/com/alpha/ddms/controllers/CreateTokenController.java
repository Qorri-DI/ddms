package com.alpha.ddms.controllers;

import com.alpha.ddms.common.JWTGenerate;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.models.ViewDealer;
import com.alpha.ddms.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/")
public class CreateTokenController {
    @Autowired
    DealerService dealerService;
    @GetMapping("createToken")
    public ResponseEntity<?> createToken(
            @RequestBody final Map<String, Object> req
    ){
        //
        String userid = req.get("userid").toString();
        String dealerId = req.get("dealerid").toString();
        Optional<ViewDealer> cekId = dealerService.findByGetIdView(dealerId);
        if (!cekId.isPresent()){
            return new ResponseEntity<>("No Data Found",HttpStatus.NO_CONTENT);
        }else{
            String token = JWTGenerate.createToken(userid+dealerId);
            String respone = "Token :"+token;
            return new ResponseEntity<>(respone,HttpStatus.OK);
        }
    }
}
