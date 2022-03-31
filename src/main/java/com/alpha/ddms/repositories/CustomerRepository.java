package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.CustomerModel;
import com.alpha.ddms.domains.DealerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    @Query("select c from CustomerModel c where c.dealerModel = :dealerId and lower(c.customer_name) " +
            "like concat('%',:customerName,'%') order by c.customer_id")
    Page<CustomerModel> getAllCustomer(@Param("dealerId") DealerModel dealerId
            ,@Param("customerName") String customerName
            , Pageable pageable);

}