package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.DealerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<DealerModel, String> {

    @Query(value = "select * from mst_dealer where dealer_code=:dealer_code",nativeQuery = true)
    Optional<DealerModel> findByCode(String dealer_code);

    @Query(value = "select * from mst_dealer where dealer_name=:dealer_name",nativeQuery = true)
    Optional<DealerModel> findByName(String dealer_name);

    @Modifying
    @Query (value = "insert into mst_dealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code) values (:dealer_code,:dealer_name,:dealer_class,:telp_number,:alamat,:dealer_status,:dealer_ext_code)",nativeQuery = true)
    int saveDealer (String dealer_code, String dealer_name, String dealer_class, String telp_number, String alamat, String dealer_status, String dealer_ext_code);

    @Modifying
    @Query(value = "update mst_dealer where dealerCode=:dealerCode and dealerName=:dealerName",nativeQuery = true)
    int updateDealer (String dealerCode,String dealerName);
}
