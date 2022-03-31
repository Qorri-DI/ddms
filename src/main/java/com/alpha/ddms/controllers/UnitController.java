package com.alpha.ddms.controllers;

import com.alpha.ddms.common.Utils;
import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.dto.ResponListDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.dto.UnitDto;
import com.alpha.ddms.dto.UnitSaveDto;
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
        String unitcode = req.get("unitCode").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int unitquantity = Integer.parseInt(req.get("unitQuantity").toString());
        String unitcolor = req.get("unitColor").toString();
        String unitstatus = req.get("unitStatus").toString();
        float averagecost = Float.parseFloat(req.get("averageCost").toString());

        Map<String,Object> response = new HashMap<>();
        UnitDto unitDto = new UnitDto();
        Optional<UnitModel> cek = unitService.findByIdunit(unitcode);

        if (cek.isEmpty()) {
            String latestId = unitService.findByIdLast(Utils.getCurrentDateTimeString()) == null
                    ? "0" : unitService.findByIdLast(Utils.getCurrentDateTimeString()).substring(13, 7);
            String IdNew = Utils.generateLatestId(latestId);
            UnitModel unitModel = new UnitModel();
            unitModel.setUnit_id(IdNew);
            unitModel.setUnit_series_name(unitseriesname);
            Optional<DealerModel> dealer = dealerService.findById(dealerid);
            unitModel.setDealerModel(dealer.get());
            unitModel.setUnit_quantity(unitquantity);
            unitModel.setUnit_color(unitcolor);
            unitModel.setUnit_status(unitstatus);
            unitModel.setAverage_cost(averagecost);

            UnitModel unitSave = unitService.saveData(unitModel);

            UnitDto unit = new UnitDto();
            unit.setUnitCode(unitcode);
            unit.setUnitSeriesName(unitseriesname);
            unit.setDealerCode(dealer.get().getDealer_code());
            unit.setUnitQuantity(unitquantity);
            unit.setUnitColor(unitcolor);
            unit.setUnitStatus(unitstatus);
            unit.setAverageCost(averagecost);

            UnitSaveDto resDto = new UnitSaveDto();
            resDto.setCode(201);
            resDto.setData(unit);
            resDto.setMessage("Process Succesed");
            resDto.setStatus("S");
            return new ResponseEntity<>(resDto, HttpStatus.CREATED);
        }else{
            Optional<UnitModel> unitData = unitService.findByIdunit(unitcode);
            unitData.get().setUnit_id(unitcode);
            unitData.get().setUnit_series_name(unitseriesname);
            Optional<DealerModel> dealer = dealerService.findById(dealerid);
            unitData.get().setDealerModel(dealer.get());
            unitData.get().setUnit_quantity(unitquantity);
            unitData.get().setUnit_color(unitcolor);
            unitData.get().setUnit_status(unitstatus);
            unitData.get().setAverage_cost(averagecost);

            UnitModel unitSave = unitService.saveData(unitData.get());

            UnitSaveDto resDto = new UnitSaveDto();
            resDto.setCode(201);
            resDto.setData(unitSave);
            resDto.setMessage("Process Succesed");
            resDto.setStatus("S");
            return new ResponseEntity<>(unitSave,HttpStatus.OK);
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

        ResponseDto resDto = new ResponseDto();
        resDto.setStatus("S");
        resDto.setCode(201);
        resDto.setMessage("Process Successed");
        resDto.setData(responList);

        return new ResponseEntity<>(resDto,HttpStatus.OK);
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
        ResponseDto resDto = new ResponseDto();
        resDto.setStatus("S");
        resDto.setCode(201);
        resDto.setMessage("Process Successed");
        resDto.setData(unit);
        return new ResponseEntity<>(resDto,HttpStatus.OK);
    }
}
