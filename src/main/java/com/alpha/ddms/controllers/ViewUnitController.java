package com.alpha.ddms.controllers;

import com.alpha.ddms.common.*;
import com.alpha.ddms.configuration.ConfigProperties;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.*;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/qry/master/unit/")
public class ViewUnitController {
    @Autowired
    UnitService unitService;
    @Autowired
    DealerService dealerService;

    @PostMapping("listAll")
    public ResponseEntity<?> listAll(
            @RequestHeader(value = "token") String token,
            @RequestBody Map<String, Object> req
    ){
        Claims claims = JWTGenerate.validToken(token);
        String id = claims.getId();
        if (id == claims.getId()){
        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        String limits = req.get("limit").toString();
//        int limet = Integer.parseInt(req.get("limit").toString());
        String offsets = req.get("offset").toString();
//        int offsat = Integer.parseInt(req.get("offset").toString());
        if (Checks.isNullOrEmpty(unitstatus) ||
                Checks.isNullOrEmpty(unitseriesname) ||
                Checks.isNullOrEmpty(dealerid)
        ){
            return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
        }
        int limit = 0;
        if (limits.isEmpty() || limits.trim().equals("")/* || limet == 0*/){
            limit = ConfigProperties.getConstant_max_limit();
        }else{
            limit = Integer.parseInt(limits);
        }
        int offset =0;
        if (offsets.isEmpty() || offsets.equals("")/*|| offsat == 0*/){
            offset = 0;
        }else {
            offset = Integer.parseInt(offsets);
        }
        List<UnitDto> unitDtoList = unitService.findByUnit(unitstatus,unitseriesname,dealerid,limit,offset);
        if (unitDtoList.size() == 0){
            return new ResponseEntity<>("No Data Found",HttpStatus.NO_CONTENT);
        }
        ResponListDto responList = new ResponListDto();
        responList.setListUnit(unitDtoList);
        responList.setDataOfRecord(unitDtoList.size());

        ResponseDto resDto = new ResponseDto();
        resDto.setStatus("S");
        resDto.setCode(201);
        resDto.setMessage("Process Successed");
        resDto.setData(responList);

        return new ResponseEntity<>(resDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{unitCode}")
    public ResponseEntity<?> getId(
            @RequestHeader(value = "token") String token,
            @PathVariable("unitCode") String unitcode
    ){
        Claims claims = JWTGenerate.validToken(token);
        String id = claims.getId();
        if (id == claims.getId()) {
            Map<String, Object> response = new HashMap<>();
            Optional<UnitModel> cekIdUnit = unitService.findByIdunit(unitcode);
            if (!Checks.isNullOrEmpty(unitcode)) {
                if (cekIdUnit.isEmpty()) {
                    return new ResponseEntity<>("No Data Found", HttpStatus.NO_CONTENT);
                } else {
                    UnitDto unit = unitService.UnitId(unitcode);
                    ResponseDto resDto = new ResponseDto();
                    resDto.setStatus("S");
                    resDto.setCode(201);
                    resDto.setMessage("Process Successed");
                    resDto.setData(unit);
                    return new ResponseEntity<>(resDto, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Error Bad Request", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
        }
    }
}
