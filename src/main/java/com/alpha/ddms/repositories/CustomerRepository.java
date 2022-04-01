package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel,String> {
}
