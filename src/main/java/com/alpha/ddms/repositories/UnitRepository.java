package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitModel, String> {
    @Override
    UnitModel save(UnitModel data);
    @Query(value = "select * from mst_unit where unit_id=?1", nativeQuery = true)
    List<UnitModel>findByIdUnit(String unit_id);
}
