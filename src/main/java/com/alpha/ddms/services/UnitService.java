package com.alpha.ddms.services;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.dto.UnitDto;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UnitService {
    @Autowired UnitRepository unitRepository;
    @Autowired DealerRepository dealerRepository;
    public UnitModel saveData(
            String unitcode,
            String unitseriesname,
            String dealerid,
            int unitquantity,
            String unitcolor,
            String unitstatus,
            int averagecost
    ){
        DealerModel dealer = dealerRepository.getById(dealerid);
        UnitModel unit = new UnitModel();
        unit.setUnit_id(unitcode);
        unit.setUnit_series_name(unitseriesname);
        unit.setDealerModel(dealer);
        unit.setUnit_quantity(unitquantity);
        unit.setUnit_color(unitcolor);
        unit.setUnit_status(unitstatus);
        unit.setAverage_cost(averagecost);
        return unitRepository.save(unit);
    }
    public List<UnitModel>findByIdUnit(String id){
        List<UnitModel> unitModelList = new ArrayList<>();
        unitModelList = unitRepository.findByIdUnit(id);
        return unitModelList;
    }
    public List<UnitModel>findByUnit(
            String unitstatus,
            String unitseriesname,
            String dealerId
    ){
        List<UnitModel>unitModelList = new ArrayList<>();
        unitModelList = unitRepository.findByData(unitstatus,unitseriesname,dealerId);
        return unitModelList;
    }
    public List<UnitDto>viewData(
            String unitcode,
            String unitseriesname,
            String dealerid,
            int unitquantity,
            String unitcolor,
            String unitstatus,
            int averagecost
    ){
        List<UnitDto>unitDtoList=new ArrayList<>();
        List<UnitModel> unitModels = unitRepository.findByIdUnit(unitcode);

        for (int i = 0; i < unitModels.size(); i++) {
            UnitDto data = new UnitDto();
            data.setUnit_id(unitcode);
            data.setUnit_series_name(unitseriesname);
            data.setDealerId(dealerid);
            data.setUnit_quantity(unitquantity);
            data.setUnit_color(unitcolor);
            data.setUnit_status(unitstatus);
            data.setAverage_cost(averagecost);
            unitDtoList.add(data);
        }
        return unitDtoList;
    }
}
