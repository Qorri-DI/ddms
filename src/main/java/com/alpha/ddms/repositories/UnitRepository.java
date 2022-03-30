package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface UnitRepository extends JpaRepository<UnitModel, String> {
    @Override
    UnitModel save(UnitModel data);
    @Query(value = "select * from mst_unit where unit_id=?1", nativeQuery = true)
    List<UnitModel>findByIdUnit(String unitId);
    @Query(value = "select * from mst_unit where dealer_code=:dealerCode and lower(unit_status)=lower(:unitStatus) and lower(unit_series_name) like lower(concat('%',:unitseriesname,'%'))",nativeQuery = true)
    Page<UnitModel> findByData(Pageable pageable,String dealerCode, String unitStatus, String unitseriesname);

    @Query("select u from UnitModel u where u.dealerModel = ?1 and lower(u.unit_status) = lower(?2) and lower(u.unit_series_name) like lower(concat('%',?3,'%'))")
    List<UnitModel> findByData2(String dealerCode, String unitStatus, String unitseriesname);
}
