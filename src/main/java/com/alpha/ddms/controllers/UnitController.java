package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.UnitModel;
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
    public ResponseEntity<?> UnitSave(
            @RequestBody Map<String, Object> req
    ){
        if (!req.isEmpty()) {
            String unitcode = req.get("unitCode").toString();
            String unitseriesname = req.get("unitSeriesName").toString();
            String dealerid = req.get("dealerId").toString();
            int unitquantity = Integer.parseInt(req.get("unitQuantity").toString());
            String unitcolor = req.get("unitColor").toString();
            String unitstatus = req.get("unitStatus").toString();
            float averagecost = Float.parseFloat(req.get("averageCost").toString());
            Optional<UnitModel> cek = unitService.findByIdunit(unitcode);
            if (unitcode.isEmpty()) {
                UnitModel unitModel = new UnitModel();
                String latestId = unitService.findByIdLast(Utils.getCurrentDateTimeString()) == null
                        ? "0" : unitService.findByIdLast(Utils.getCurrentDateTimeString()).substring(13, 7);
                String IdNew = Utils.generateLatestId(latestId);
                unitModel.setUnit_id(IdNew);
                unitModel.setUnit_series_name(unitseriesname);
                DealerModel dealerModel = dealerService.getDealer(dealerid);
                unitModel.setDealerModel(dealerModel);
                unitModel.setUnit_quantity(unitquantity);
                unitModel.setUnit_color(unitcolor);
                unitModel.setUnit_status(unitstatus);
                unitModel.setAverage_cost(averagecost);
                UnitModel unit = unitService.saveData(unitModel);
                return new ResponseEntity<>(unitModel, HttpStatus.OK);
            } else {
                UnitModel unitModel = unitService.findIdUnit(unitcode);
                unitModel.setUnit_series_name(unitseriesname);
                DealerModel dealerModel = dealerService.getDealer(dealerid);
                unitModel.setDealerModel(dealerModel);
                unitModel.setUnit_quantity(unitquantity);
                unitModel.setUnit_color(unitcolor);
                unitModel.setUnit_status(unitstatus);
                unitModel.setAverage_cost(averagecost);
                UnitModel unit = unitService.saveData(unitModel);
                return new ResponseEntity<>(unit, HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>("Error Bad Request ", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("qry/master/unit/listAll")
    public ResponseEntity<?> listAll(
            @RequestBody Map<String, Object> req
    ){
        Map<String,Object> response = new HashMap<>();
        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int offset = Integer.parseInt(req.get("offset").toString());
        int limit = Integer.parseInt(req.get("limit").toString());
        List<UnitDto> unitDtoList = unitService.findByUnit(unitstatus,unitseriesname,dealerid,limit,offset);

        ResponListDto responList = new ResponListDto();
        responList.setListUnit(unitDtoList);
        responList.setDataOfRecord(unitDtoList.size());

        response.put("Status","S");
        response.put("code",201);
        response.put("message","Process Successed");
        response.put("data",responList);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("qry/master/unit/get/{unitCode}")
    public ResponseEntity<?> getId(
            @PathVariable("unitCode") String unitcode
    ){
        Map<String,Object> response = new HashMap<>();
        Optional<UnitModel> cekIdUnit = unitService.findByIdunit(unitcode);
        if (cekIdUnit.isEmpty()){
            return new ResponseEntity<>("No Data Found",HttpStatus.NO_CONTENT);
        }
        UnitDto unit = unitService.UnitId(unitcode);
        response.put("Status","S");
        response.put("code",201);
        response.put("message","Process Successed");
        response.put("data",unit);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
