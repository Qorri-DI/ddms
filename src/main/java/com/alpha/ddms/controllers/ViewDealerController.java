package com.alpha.ddms.controllers;


import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.dto.DealerDTO;
import com.alpha.ddms.dto.DealerDTOlist;
import com.alpha.ddms.dto.DealerDtoById;
import com.alpha.ddms.dto.DealerDtoListAll;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.utils.Check;
import com.alpha.ddms.utils.GenerateJwt;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class ViewDealerController {

    @Autowired
    DealerService dealerService;

    @PostMapping("listAll")
    public ResponseEntity<Object> listAll(
            @RequestHeader("token")String token,
            @RequestHeader("userId")String userId,
            @RequestBody final Map<String, Object> request
    ) {
        Claims claims = GenerateJwt.validateToken(token);
        String id = claims.getId();
        if (id.equals(userId)) {
            String dealer_code = request.get("dealerId").toString();
            String dealer_name = request.get("dealerName").toString().toLowerCase(Locale.ROOT);
            String dealer_status = request.get("dealerStatus").toString().toLowerCase(Locale.ROOT);
            int offset = Integer.parseInt(request.get("offset").toString());
            int limit = Integer.parseInt(request.get("limit").toString());

            List<DealerDTO> response = dealerService.listAll(dealer_code, dealer_status, dealer_name, offset, limit);

            DealerDTOlist dtl = new DealerDTOlist();
            dtl.setListDealer(response);
            dtl.setDataOfRecord(response.size());
            if(response.size()==0){
                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
            }else {
                DealerDtoListAll dl = new DealerDtoListAll();
                dl.setStatus("S");
                dl.setCode(201);
                dl.setMessage("Process Successed");
                dl.setData(dtl);
                return new ResponseEntity<>(dl, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>("UserId Salah",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "get/{dealerId}")
    public ResponseEntity<Object> detById(
            @RequestHeader("token")String token,
            @RequestHeader("userId")String userId,
            @PathVariable String dealerId
    ) {
        Claims claims = GenerateJwt.validateToken(token);
        String id = claims.getId();
        if (id.equals(userId)) {
            DealerDTO dealerDTOList = new DealerDTO();
            try {
                dealerDTOList = dealerService.dealerById(dealerId);
                Optional<DealerModel> dealerCode = dealerService.findByGetId(dealerId);
                if (!Check.isNullOrEmpty(dealerId)) {
                    DealerDtoById dealerDtoById = new DealerDtoById();
                    dealerDtoById.setStatus("S");
                    dealerDtoById.setCode(201);
                    dealerDtoById.setMessage("Process Successed");
                    dealerDtoById.setData(dealerDTOList);
                    return new ResponseEntity<>(dealerDtoById, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
                }
            } catch (NullPointerException e) {
                return new ResponseEntity<>("data tidak ada", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("UserId Salah",HttpStatus.BAD_REQUEST);
        }
    }

}
