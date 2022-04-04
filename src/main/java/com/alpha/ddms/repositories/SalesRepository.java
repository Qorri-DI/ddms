package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface SalesRepository extends JpaRepository<SalesModel, String> {
    @Query(value = "select * from mst_sales where supervisor_id = '0' and sales_id = ?1", nativeQuery = true)
    Optional<SalesModel> findSupervisorId(String supervisorId);

    @Query(value = "select * from mst_sales where sales_id LIKE concat(?1,'%') " +
            "order by sales_id DESC limit 1",nativeQuery = true)
    Optional<SalesModel> findLatestId(String salesId);

    @Query(value = "select * from mst_sales " +
            "where lower(sales_name) like lower(concat('%',:salesName,'%')) " +
            "and dealer_code like concat('%',:dealerId,'%') " +
            "and lower(sales_status) like lower(concat('%',:salesStatus,'%')) " +
            "limit :limit offset :offset", nativeQuery = true)
    List<SalesModel> searchSalesModel(String salesName, String dealerId, String salesStatus,
                                      int offset, int limit);
}
