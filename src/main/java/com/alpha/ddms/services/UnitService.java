package com.alpha.ddms.services;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.repositories.DealerRepository;
import com.alpha.ddms.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public List<UnitDto>findByUnit(
            String unitstatus,
            String unitseriesname,
            String dealerId,
            int limit,
            int offset
    ){

        Page<UnitModel> listAll = unitRepository.findByData((PageRequest.of(offset,limit)),dealerId,unitstatus,unitseriesname);
//        List<UnitModel> listAll = unitRepository.findByData2(dealerRepository.findById(dealerId).get().getDealer_code(),unitstatus,unitseriesname);
        List<UnitDto>unitDtoList = new ArrayList<>();
        List<UnitModel>unitList = listAll.toList();
        for (UnitModel unit : unitList){
            UnitDto unitDto = new UnitDto();
            unitDto.setUnitCode(unit.getUnit_id());
            unitDto.setUnitSeriesName(unit.getUnit_series_name());
            unitDto.setDealerCode(unit.getDealerModel().getDealer_code());
            unitDto.setUnitQuantity(unit.getUnit_quantity());
            unitDto.setUnitColor(unit.getUnit_color());
            unitDto.setUnitStatus(unit.getUnit_status());
            unitDto.setAverageCost(unit.getAverage_cost());
            unitDtoList.add(unitDto);
        }
        return unitDtoList;
    }
//    public List<ResponDto> unitListAll(){
//        List<ResponDto> responDtos = new ArrayList<>();
//        List<UnitDto>unitDtoList = findByUnit()
//        responDtos
//    }
    @Autowired
    UnitRepository unitRepository;

    public Optional<UnitModel> findById(String unitCode){
        return unitRepository.findById(unitCode);
    }

}
