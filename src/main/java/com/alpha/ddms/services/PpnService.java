package com.alpha.ddms.services;

import com.alpha.ddms.domains.PpnModel;
import com.alpha.ddms.repositories.PpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PpnService {

    @Autowired
    PpnRepository ppnRepository;

    public Optional<PpnModel>findPpnId(String id){
        Optional<PpnModel>getPpnId = ppnRepository.findById(id);

        return getPpnId;
    }

    public PpnModel savePpn(PpnModel ppnModel){
        PpnModel save = ppnRepository.save(ppnModel);

        return save;
    }
    public PpnModel updatePpn(PpnModel ppnModel){
        PpnModel save = ppnModel;

        return save;
    }
}