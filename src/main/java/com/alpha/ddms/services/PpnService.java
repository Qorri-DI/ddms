package com.alpha.ddms.services;

import com.alpha.ddms.domains.PpnModel;
import com.alpha.ddms.repositories.PpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PpnService {

    @Autowired
    PpnRepository ppnRepository;

    @Autowired
    Environment env;

    public Optional<PpnModel>findPpnId(String id){
        Optional<PpnModel>getPpnId = ppnRepository.findById(id);

        return getPpnId;
    }


    public Page<PpnModel> findds(String dealerId, String status, Integer offset, Integer limit){

        if (offset == 0 || offset == null){
            offset = 0;
        }

        if (limit == 0 || limit == null){
            limit = Integer.parseInt(env.getProperty("Constants_MAX_LIMIT"));
        }
        Page<PpnModel>getds = ppnRepository.findlist(dealerId,status, PageRequest.of(offset,limit));

        return getds;
    }

    public Optional<PpnModel>findactiveppn(String dealerId,Date qd){


        Optional<PpnModel>getactiveppn = ppnRepository.findactiveppn(dealerId,qd);

        return getactiveppn;
    }


    public Optional<PpnModel>findesd(Date esd){
        Optional<PpnModel>getesd = ppnRepository.findesd(esd);

        return getesd;

    }

    public Optional<PpnModel>findQueryDate(String effective_start_date){
        Optional<PpnModel>getquerydate = ppnRepository.findquery(effective_start_date);

        return getquerydate;
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