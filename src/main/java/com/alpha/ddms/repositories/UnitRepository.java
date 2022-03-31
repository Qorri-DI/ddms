package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UnitRepository extends JpaRepository<UnitModel, String> {
    @Override
    UnitModel save(UnitModel data);
    @Query(value = "select * from mst_unit where unit_id=:unitId", nativeQuery = true)
    List<UnitModel>findByIdUnit(String unitId);
    @Query(value = "select * from mst_unit where dealer_code=:dealerCode and lower(unit_status)=lower(:unitStatus) and lower(unit_series_name) like lower(concat('%',:unitseriesname,'%'))",nativeQuery = true)
    Page<UnitModel> findByData(Pageable pageable,String dealerCode, String unitStatus, String unitseriesname);
    @Query(value = "select * from mst_unit where unit_id LIKE concat(?1,'%') order by unit_id DESC limit 1",nativeQuery = true)
    Optional<UnitModel> findLatestId(String IdUnit);
}
