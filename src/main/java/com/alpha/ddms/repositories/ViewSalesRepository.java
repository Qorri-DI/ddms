package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.SalesModel;
import com.alpha.ddms.models.ViewSales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewSalesRepository extends JpaRepository<ViewSales,String> {
    @Query(value = "select * from vw_mst_sales " +
            "where lower(sales_name) like lower(concat('%',:salesName,'%')) " +
            "and dealer_code like concat('%',:dealerId,'%') " +
            "and lower(sales_status) like lower(concat('%',:salesStatus,'%'))",
            nativeQuery = true)
    Page<ViewSales> searchSalesModel(String salesName, String dealerId, String salesStatus,
                                     Pageable pageable);
}
