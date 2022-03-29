package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    @Query("select c from CustomerModel c order by c.customer_id")
    Page<CustomerModel> getAllCustomer(Pageable pageable);

}
