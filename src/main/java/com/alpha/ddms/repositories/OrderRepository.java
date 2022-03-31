package com.alpha.ddms.repositories;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel,String> {
    @Query(value = "select * from trx_order where order_id LIKE concat(?1,'%') " +
            "order by order_id DESC limit 1",nativeQuery = true)
    Optional<OrderModel> findLatestId(String orderId);

    @Query("select o from OrderModel o where " +
            "o.dealerModel = concat('%',?1,'%')  and " +
            "lower(o.plat_nomor) like concat('%',?2,'%') and " +
            "lower(o.nomor_mesin) like concat('%',?3,'%') and " +
            "lower(o.nomor_rangka) like concat('%',?4,'%') and " +
            "o.payment_status = ?5")
    Page<OrderModel> getAllOrder(DealerModel dealerModel,
                                 String platNomor,
                                 String nomor_mesin,
                                 String nomor_rangka,
                                 String paymentStatus,
                                 Pageable pageable);

}
