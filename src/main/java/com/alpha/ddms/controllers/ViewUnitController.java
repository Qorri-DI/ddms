package com.alpha.ddms.controllers;

import com.alpha.ddms.common.*;
import com.alpha.ddms.configuration.ConfigProperties;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.*;
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
            @RequestBody Map<String, Object> req
    ){

        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int offset = Integer.parseInt(req.get("offset").toString());
        int limit = Integer.parseInt(req.get("limit").toString());
        if (Checks.isNullOrEmpty(unitstatus) ||
                Checks.isNullOrEmpty(unitseriesname) ||
                Checks.isNullOrEmpty(dealerid)
        ){
            return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
        }else if (limit == 0){
            limit = ConfigProperties.getConstant_max_limit();
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
    }

    @GetMapping("get/{unitCode}")
    public ResponseEntity<?> getId(
            @PathVariable("unitCode") String unitcode
    ){
        Map<String,Object> response = new HashMap<>();
        Optional<UnitModel> cekIdUnit = unitService.findByIdunit(unitcode);
        if (!Checks.isNullOrEmpty(unitcode)){
            if (cekIdUnit.isEmpty()) {
                return new ResponseEntity<>("No Data Found", HttpStatus.NO_CONTENT);
            }else{
                UnitDto unit = unitService.UnitId(unitcode);
                ResponseDto resDto = new ResponseDto();
                resDto.setStatus("S");
                resDto.setCode(201);
                resDto.setMessage("Process Successed");
                resDto.setData(unit);
                return new ResponseEntity<>(resDto,HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
        }
    }
}
