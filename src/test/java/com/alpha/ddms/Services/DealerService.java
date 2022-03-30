package com.alpha.ddms.Services;

import com.alpha.ddms.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;
}
