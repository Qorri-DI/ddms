package com.alpha.ddms.services;

import com.alpha.ddms.domains.UnitModel;
import com.alpha.ddms.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UnitService {
    @Autowired
    UnitRepository unitRepository;

    public Optional<UnitModel> findById(String unitCode){
        return unitRepository.findById(unitCode);
    }
}
