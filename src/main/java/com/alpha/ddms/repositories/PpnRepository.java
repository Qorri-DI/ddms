package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.PpnModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PpnRepository extends JpaRepository<PpnModel, String> {
    @Query(value = "select ppn_id from mst_ppn where ppn_id = :id",nativeQuery = true)
    Optional<PpnModel> findppnid(String id);


}