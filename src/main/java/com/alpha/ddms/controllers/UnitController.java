package com.alpha.ddms.controllers;

import com.alpha.ddms.common.*;
import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/unit/")
public class UnitController {
    @Autowired
    UnitService unitService;
    @Autowired
    DealerService dealerService;

    @PostMapping("save")
    public ResponseEntity<?> UnitSave(
            @RequestBody Map<String, Object> req
    ){
        String unitcode = req.get("unitCode").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int unitquantity = Integer.parseInt(req.get("unitQuantity").toString());
        String unitcolor = req.get("unitColor").toString();
        String unitstatus = req.get("unitStatus").toString();
        float averagecost = Float.parseFloat(req.get("averageCost").toString());
        UnitModel unit = new UnitModel();
        UnitDto unitDto = new UnitDto();
        Optional<UnitModel> unitData = unitService.findByIdunit(unitcode);

        if (unitData.isEmpty()) {
            String latestId = unitService.findByIdLast(Utils.getCurrentDateTimeString()) == null
                    ? "0" : unitService.findByIdLast(Utils.getCurrentDateTimeString()).substring(13, 7);
            String IdNew = Utils.generateLatestId(latestId);
            if (Checks.isNullOrEmpty(unitseriesname) ||
                    Checks.isNullOrEmpty(dealerid) ||
                    unitquantity == 0 ||
                    Checks.isNullOrEmpty(unitcolor) ||
                    Checks.isNullOrEmpty(unitstatus) ||
                    averagecost == 0
            ){
                return new ResponseEntity<>("Error Bad Request",HttpStatus.BAD_REQUEST);
            }
            unit.setUnit_id(IdNew);
            unit.setUnit_series_name(unitseriesname);
            Optional<DealerModel> dealer = dealerService.findById(dealerid);
            unit.setDealerModel(dealer.get());
            unit.setUnit_quantity(unitquantity);
            unit.setUnit_color(unitcolor);
            unit.setUnit_status(unitstatus);
            unit.setAverage_cost(averagecost);

            UnitModel unitSave = unitService.saveData(unit);

            unitDto.setUnitCode(IdNew);
            unitDto.setUnitSeriesName(unitseriesname);
            unitDto.setDealerCode(dealer.get().getDealer_code());
            unitDto.setUnitQuantity(unitquantity);
            unitDto.setUnitColor(unitcolor);
            unitDto.setUnitStatus(unitstatus);
            unitDto.setAverageCost(averagecost);

            UnitSaveDto resDto = new UnitSaveDto();
            resDto.setCode(201);
            resDto.setData(unit);
            resDto.setMessage("Process Succesed");
            resDto.setStatus("S");
            return new ResponseEntity<>(resDto, HttpStatus.CREATED);
        }else{
            if(unitData.isEmpty()){
                return new ResponseEntity<>("No Data Found",HttpStatus.NO_CONTENT);
            }else{
                unit.setUnit_id(unitcode);
                if (Checks.isNullOrEmpty(unitseriesname)){
                    unitseriesname.equals(unitData.get().getUnit_series_name());
                    unit.setUnit_series_name(unitseriesname);
                }else {
                    unit.setUnit_series_name(unitseriesname);
                }if (Checks.isNullOrEmpty(dealerid)){
                    Optional<DealerModel> dealer = dealerService.findById(unitData.get().getDealerModel().getDealer_code());
                    unit.setDealerModel(dealer.get());
                }else {
                    Optional<DealerModel> dealer = dealerService.findById(dealerid);
                    unit.setDealerModel(dealer.get());
                }if (unitquantity == 0){
                    unitquantity = unitData.get().getUnit_quantity();
                    unit.setUnit_quantity(unitquantity);
                }else {
                    unit.setUnit_quantity(unitquantity);
                }if (Checks.isNullOrEmpty(unitcolor)){
                    unit.setUnit_color(unitData.get().getUnit_color());
                }else {
                    unit.setUnit_color(unitcolor);
                }if (Checks.isNullOrEmpty(unitstatus)){
                    unit.setUnit_status(unitData.get().getUnit_status());
                }else {
                    unit.setUnit_status(unitstatus);
                }if (averagecost == 0){
                    averagecost = unitData.get().getAverage_cost();
                    unit.setAverage_cost(unitData.get().getAverage_cost());
                }else{
                    unit.setAverage_cost(averagecost);
                }
                UnitModel unitSave = unitService.saveData(unit);
            }

            unitDto.setUnitCode(unitData.get().getUnit_id());
            unitDto.setUnitSeriesName(unitData.get().getUnit_series_name());
            unitDto.setDealerCode(unitData.get().getDealerModel().getDealer_code());
            unitDto.setUnitQuantity(unitData.get().getUnit_quantity());
            unitDto.setUnitColor(unitData.get().getUnit_color());
            unitDto.setUnitStatus(unitData.get().getUnit_status());
            unitDto.setAverageCost(unitData.get().getAverage_cost());

            UnitSaveDto resDto = new UnitSaveDto();
            resDto.setCode(201);
            resDto.setData(unitDto);
            resDto.setMessage("Process Succesed");
            resDto.setStatus("S");
            return new ResponseEntity<>(resDto,HttpStatus.OK);
        }
    }
}
