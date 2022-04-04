package com.alpha.ddms.services;

import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.models.*;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UnitService {
    @Autowired UnitRepository unitRepository;
    @Autowired DealerRepository dealerRepository;
    @Autowired ViewUnitRepository viewUnitRepository;
    public UnitModel saveData(UnitModel unit){
        return unitRepository.save(unit);
    }
    public List<UnitModel>findByIdUnit(String id){
        List<UnitModel> unitModelList = new ArrayList<>();
        unitModelList = unitRepository.findByIdUnit(id);
        return unitModelList;
    }
    public UnitModel findIdUnit(String idUnit){
        Optional<UnitModel> unitModel = unitRepository.findById(idUnit);
        return !unitModel.isPresent() ? null : unitModel.get();
    }
    public Optional<UnitModel> findByIdunit(String idUnit){
        return unitRepository.findById(idUnit);
    }
    public String findByIdLast(String IdUnit){
        Optional<UnitModel>unitModel=unitRepository.findLatestId(IdUnit);
        return !unitModel.isPresent() ? null : unitModel.get().getUnit_id();
    }
    public List<UnitDto>findByUnit(
            String unitstatus,
            String unitseriesname,
            String dealerId,
            int limit,
            int offset
    ){

        Page<ViewUnit> listAll = viewUnitRepository.findByData((PageRequest.of(offset,limit)),dealerId,unitstatus,unitseriesname);
        List<UnitDto>unitDtoList = new ArrayList<>();
        List<ViewUnit>unitList = listAll.toList();
        for (ViewUnit unit : unitList){
            UnitDto unitDto = new UnitDto();
            unitDto.setUnitCode(unit.getUnit_id());
            unitDto.setUnitSeriesName(unit.getUnit_series_name());
            Optional<DealerModel>dealer = dealerRepository.findById(dealerId);
            unitDto.setDealerCode(dealer.get().getDealer_code());
            unitDto.setUnitQuantity(unit.getUnit_quantity());
            unitDto.setUnitColor(unit.getUnit_color());
            unitDto.setUnitStatus(unit.getUnit_status());
            unitDto.setAverageCost(unit.getAverage_cost());
            unitDtoList.add(unitDto);
        }
        return unitDtoList;
    }
    public UnitDto UnitId(String idUnit){
        List<ViewUnit>unitID = viewUnitRepository.findByIdUnit(idUnit);
        UnitDto unitDto = new UnitDto();

        for (ViewUnit unit : unitID){
            unitDto.setUnitCode(unit.getUnit_id());
            unitDto.setUnitSeriesName(unit.getUnit_series_name());
            unitDto.setDealerCode(unit.getDealerModel().getDealer_code());
            unitDto.setUnitQuantity(unit.getUnit_quantity());
            unitDto.setUnitColor(unit.getUnit_color());
            unitDto.setUnitStatus(unit.getUnit_status());
            unitDto.setAverageCost(unit.getAverage_cost());
        }
        return unitDto;
    }
}
