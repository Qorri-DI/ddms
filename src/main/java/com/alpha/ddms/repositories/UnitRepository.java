package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitModel, String> {
    @Override
    UnitModel save(UnitModel data);
    @Query(value = "select * from mst_unit where unit_id=?1", nativeQuery = true)
    List<UnitModel>findByIdUnit(String unitId);
    @Query(value = "select * from mst_unit where dealer_code=?1 and lower(unit_status)=?2 and  lower(unit_series_name) like ?3%", nativeQuery = true)
    List<UnitModel>findByData(String dealerCode, String unitStatus, String unitSeriesName, int limit, int offset);

}
