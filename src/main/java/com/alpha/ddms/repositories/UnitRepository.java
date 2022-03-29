package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface UnitRepository extends JpaRepository<UnitModel, String> {
}
