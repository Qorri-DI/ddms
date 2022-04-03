package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.models.ViewCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ViewCustomerRepository extends JpaRepository<ViewCustomer,String > {
    @Query("select c from ViewCustomer c where c.dealerModel = :dealerId and lower(c.customer_name) " +
            "like lower(concat('%',:customerName,'%')) order by c.customer_id")
    Page<ViewCustomer> getAllCustomer(@Param("dealerId") DealerModel dealerId
            , @Param("customerName") String customerName
            , Pageable pageable);
}
