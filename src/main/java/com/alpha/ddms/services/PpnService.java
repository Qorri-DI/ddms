package com.alpha.ddms.services;

import com.alpha.ddms.repositories.PpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PpnService {
    @Autowired
    PpnRepository ppnRepository;
}
