package com.alpha.ddms.repositories;

import com.alpha.ddms.models.ViewDealer;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface ViewDealerRepository  extends JpaRepository<ViewDealer, String> {
    @Query(value = "select * from vw_mst_dealer where dealer_code=:dealerId",nativeQuery = true)
    Optional<ViewDealer> findByCodeView(String dealerId);

    @Query(value = "select * from vw_mst_dealer where dealer_code like :dealerId",nativeQuery = true)
    ViewDealer findByDealerId(String dealerId);

    //   @Query(value = "select * from mst_dealer where dealer_code =:dealer_code% and lower(dealer_status)=:dealer_status and lower(dealer_name) like %:dealer_name% order by dealer_code ASC",nativeQuery = true)
    // Page<DealerModel> getAllPage (Pageable pageable,String dealer_code,String dealer_status, String dealer_name);

    @Query(value = "select * from vw_mst_dealer where dealer_code = :dealer_code or lower(dealer_status)=:dealer_status or lower(dealer_name) like %:dealer_name% order by dealer_code ASC",nativeQuery = true)
    Page<ViewDealer> getAllPage (Pageable pageable, String dealer_code, String dealer_status, String dealer_name);
}
