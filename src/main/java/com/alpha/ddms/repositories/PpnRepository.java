package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.PpnModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PpnRepository extends JpaRepository<PpnModel, String> {
    @Query(value = "select ppn_id from mst_ppn where ppn_id = :id",nativeQuery = true)
    Optional<PpnModel> findppnid(String id);

    @Query(value = "select * from mst_ppn where dealer_code = :dealer_code", nativeQuery = true)
    Optional<PpnModel>finddealerid(String dealer_code);

    @Query(value = "select * from mst_ppn where dealer_code like %:dealerId% and ppn_status like %:status%",nativeQuery = true)
    Page<PpnModel> findlist(String dealerId, String status, Pageable pageable);

    @Query(value = "selec * from mst_ppn where ppn_status = :status",nativeQuery = true)
    Optional<PpnModel>caristatus(String status);

    @Query(value = "select * from mst_ppn where dealer_code = :dealerId and effective_start_date >= :qd limit 1",nativeQuery = true)
    Optional<PpnModel>findactiveppn(String dealerId, Date qd);

    @Query(value = "select * from mst_ppn where effective_start_date = :esd",nativeQuery = true)
    Optional<PpnModel>findesd(Date esd);

    @Query(value = "select * from mst_ppn where effective_start_date = :effective_start_date", nativeQuery = true)
    Optional<PpnModel>findquery(String effective_start_date);
}
