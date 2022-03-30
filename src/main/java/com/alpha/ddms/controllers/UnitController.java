package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.dto.UnitDto;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/unit")
public class UnitController {
    @Autowired
    UnitService unitService;
    @Autowired
    DealerService dealerService;

    @PostMapping("save")
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
    @PostMapping("listAll")
    public List<UnitModel> listAll(
            @RequestBody Map<String, Object> req
    ){
        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int offset = Integer.parseInt(req.get("offset").toString());
        int limit = Integer.parseInt(req.get("limit").toString());
        List<UnitModel> unitModelList = unitService.findByUnit(unitstatus,unitseriesname,dealerid);
        return unitModelList;
    }
    @GetMapping("get/{unitCode}")
    public List<UnitModel> getId(
            @PathVariable("unitCode") String unitcode
    ){
        List<UnitModel> unit = unitService.findByIdUnit(unitcode);
        return unit;
    }
}
