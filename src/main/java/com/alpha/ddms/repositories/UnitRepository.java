package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<UnitModel, String> {
    @Override
    UnitModel save(UnitModel data);

}
