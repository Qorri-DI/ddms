package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.dto.ResponDto;
import com.alpha.ddms.dto.ResponListDto;
import com.alpha.ddms.dto.UnitDto;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1")
public class UnitController {
    @Autowired
    UnitService unitService;
    @Autowired
    DealerService dealerService;

    @PostMapping("/cmd/master/unit/save")
    public ResponseEntity<Object> UnitSave(
            @RequestBody Map<String, Object> req
    ){
        String unitcode = req.get("unitCode").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int unitquantity = Integer.parseInt(req.get("unitQuantity").toString());
        String unitcolor = req.get("unitColor").toString();
        String unitstatus = req.get("unitStatus").toString();
        int averagecost = Integer.parseInt(req.get("averageCost").toString());

        List<UnitModel>unit = new ArrayList<>();

        if (dealerService.getDealer(dealerid) == null){
            return new ResponseEntity<>("Kosong",HttpStatus.BAD_REQUEST);
        }
        unit.add(unitService.saveData(unitcode,unitseriesname,dealerid,unitquantity,unitcolor,unitstatus,averagecost));

        return new ResponseEntity<>(unit,HttpStatus.OK);
    }
    @PostMapping("qry/master/unit/listAll")
    public ResponseEntity<Object> listAll(
            @RequestBody Map<String, Object> req
    ){
        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int offset = Integer.parseInt(req.get("offset").toString());
        int limit = Integer.parseInt(req.get("limit").toString());
        List<UnitDto> unitDtoList = unitService.findByUnit(unitstatus,unitseriesname,dealerid,limit,offset);
        List<ResponListDto>respon= new ArrayList<>();
        List<ResponDto> units = new ArrayList<>();

        ResponListDto responList = new ResponListDto();
        responList.setListUnit(unitDtoList);
        responList.setDataOfRecord(unitDtoList.size());
        respon.add(responList);

        ResponDto responDto = new ResponDto();
        responDto.setStatus("S");
        responDto.setCode(201);
        responDto.setMessage("Prosess Successed");
        responDto.setData(respon);
        units.add(responDto);

        return new ResponseEntity<>(units,HttpStatus.OK);
    }
    @GetMapping("qry/master/unit/get/{unitCode}")
    public List<UnitModel> getId(
            @PathVariable("unitCode") String unitcode
    ){
        List<UnitModel> unit = unitService.findByIdUnit(unitcode);
        return unit;
    }
}
