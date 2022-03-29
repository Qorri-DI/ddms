package com.alpha.ddms.services;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
