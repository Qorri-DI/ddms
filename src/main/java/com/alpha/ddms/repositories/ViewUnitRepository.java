package com.alpha.ddms.repositories;

import com.alpha.ddms.models.ViewUnit;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewUnitRepository extends JpaRepository<ViewUnit, String> {
    @Query(value = "select * from vs_mst_unit where unit_id=:unitId", nativeQuery = true)
    List<ViewUnit> findByIdUnit(String unitId);
    @Query(value = "select * from vs_mst_unit where dealer_code=:dealerCode and lower(unit_status)=lower(:unitStatus) and lower(unit_series_name) like lower(concat('%',:unitseriesname,'%'))",nativeQuery = true)
    Page<ViewUnit> findByData(Pageable pageable, String dealerCode, String unitStatus, String unitseriesname);
//    @Query(value = "select * from vs_mst_unit where dealer_code=:dealerCode or lower(unit_status)=lower(:unitStatus) or lower(unit_series_name) like lower(concat('%',:unitseriesname,'%'))",nativeQuery = true)
//    Page<ViewUnit> findByData(Pageable pageable, String dealerCode, String unitStatus, String unitseriesname);

}
