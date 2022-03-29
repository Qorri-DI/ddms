package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.dto.UnitDto;
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
        unit.add(unitService.saveData(unitcode,unitseriesname,dealerid,unitquantity,unitcolor,unitstatus,averagecost));

        return new ResponseEntity<>(unit,HttpStatus.OK);
    }
    @PostMapping("listAll")
    public String listAll(
            @RequestBody Map<String, Object> req
    ){
        String unitstatus = req.get("unitStatus").toString();
        String unitseriesname = req.get("unitSeriesName").toString();
        String dealerid = req.get("dealerId").toString();
        int unitquantity = Integer.parseInt(req.get("unitQuantity").toString());
        int offset = Integer.parseInt(req.get("offset").toString());
        int limit = Integer.parseInt(req.get("limit").toString());
        return "UnitSave";
    }
    @PostMapping("get/{unitCode}")
    public String getId(
            @PathVariable String unitCode
    ){

        return "UnitSave";
    }
}
