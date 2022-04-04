package com.alpha.ddms.controllers;

import com.alpha.ddms.dto.*;
import com.alpha.ddms.models.ViewDealer;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.utils.*;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class ViewDealerController {

    @Autowired
    DealerService dealerService;

    @PostMapping("listAll")
    public ResponseEntity<Object> listAll(
            @RequestBody final Map<String, Object> request,
            @RequestHeader(value = "token") String token,
            @RequestHeader(value = "userId") String userId
    ) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Claims claims = GenerateJwt.validateToken(token);
            if (claims.getId().equals(userId)) {
                String dealer_code = request.get("dealerId").toString();
                String dealer_name = request.get("dealerName").toString().toLowerCase(Locale.ROOT);
                String dealer_status = request.get("dealerStatus").toString().toLowerCase(Locale.ROOT);
                int offset = Integer.parseInt(request.get("offset").toString());
                int limit = Integer.parseInt(request.get("limit").toString());

                if(!dealer_code.isEmpty()) {
                    if(!dealer_name.isEmpty()){
                        if (!dealer_status.isEmpty()){
                            List<DealerDTO> response = dealerService.listAllAAA(dealer_code, dealer_status, dealer_name, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }else {
                            List<DealerDTO> response = dealerService.listAllAAK(dealer_code, dealer_name, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }
                    }else{
                        if (dealer_name.isEmpty()){
                            List<DealerDTO> response = dealerService.listAllAKA(dealer_code, dealer_status, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }else {
                            List<DealerDTO> response = dealerService.listAllAKK(dealer_code, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }
                    }
                }else {
                    if(!dealer_name.isEmpty()){
                        if (!dealer_status.isEmpty()){
                            List<DealerDTO> response = dealerService.listAllKAA(dealer_status, dealer_name, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }else {
                            List<DealerDTO> response = dealerService.listAllKAK(dealer_name, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }
                    }else {
                        if (!dealer_status.isEmpty()){
                            List<DealerDTO> response = dealerService.listAllKKA(dealer_status, offset, limit);

                            DealerDTOlist dtl = new DealerDTOlist();
                            dtl.setListDealer(response);
                            dtl.setDataOfRecord(response.size());
                            if (response.size() == 0) {
                                return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                            } else {
                                DealerDtoListAll dl = new DealerDtoListAll();
                                dl.setStatus("S");
                                dl.setCode(201);
                                dl.setMessage("Process Successed");
                                dl.setData(dtl);
                                return new ResponseEntity<>(dl, HttpStatus.OK);
                            }
                        }else {
                            return new ResponseEntity<>("data tidak ada", HttpStatus.NO_CONTENT);
                        }
                    }
                }

            } else {
                return new ResponseEntity<>("UserId Salah", HttpStatus.BAD_REQUEST);
            }
        } catch (ExpiredJwtException e) {

            ret.put("status", "JWT Expired");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);

        } catch (SignatureException e) {

            ret.put("status", "JWT fail");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ret.put("status", "JWT fail");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "get/{dealerId}")
    public ResponseEntity<Object> detById(
            @PathVariable(required = false, value = "dealerId") String dealerId,
            @RequestHeader(value = "token") String token,
            @RequestHeader(value = "userId") String userId
    ) {

        Map<String, Object> ret = new HashMap<>();
        try {
            Claims claims = GenerateJwt.validateToken(token);
            if (claims.getId().equals(userId)) {

                DealerDTO dealerDTOList = new DealerDTO();
                try {
                    dealerDTOList = dealerService.dealerById(dealerId);
                    Optional<ViewDealer> dealerCode = dealerService.findByGetIdView(dealerId);
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
            } else {
                return new ResponseEntity<>("UserId Salah", HttpStatus.BAD_REQUEST);
            }
        } catch (ExpiredJwtException e) {

            ret.put("status", "JWT Expired");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);

        } catch (SignatureException e) {

            ret.put("status", "JWT fail");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ret.put("status", "JWT fail");
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        }
    }
}
