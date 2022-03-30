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
    @Query(value = "select * from mst_unit where unit_status=?1 or  unit_series_name=?2 or dealer_code=?3 order by unit_id", nativeQuery = true)
    List<UnitModel>findByData(String unitStatus, String unitSeriesName, String dealerId);
}
