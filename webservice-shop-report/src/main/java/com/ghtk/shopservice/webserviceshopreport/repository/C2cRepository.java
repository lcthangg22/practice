package com.ghtk.shopservice.webserviceshopreport.repository;

import com.ghtk.shopservice.webserviceshopreport.model.C2cNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface C2cRepository extends JpaRepository<C2cNotification, Integer> {
    List<C2cNotification> getC2cNotificationByShopId(Integer shop_id, Pageable pageable);

    int countC2cNotificationByShopIdAndCreatedAfter(Integer shop_id, Date date);
}
