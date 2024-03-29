package com.ghtk.shopservice.webserviceshopreport.repository;

import com.ghtk.shopservice.webserviceshopreport.model.B2cNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface B2cRepository extends JpaRepository<B2cNotification, Integer> {
    @Query("SELECT b FROM B2cNotification b WHERE b.shopId = ?1 AND b.role = ?2 AND b.created BETWEEN ?3 AND ?4 ORDER BY b.created DESC")
    List<B2cNotification> getMobileNotificationsV2(Integer shop_id, String role, Date created_from, Date created_to, Integer type, Pageable pageable);

    @Query("SELECT COUNT(b.shopId) FROM B2cNotification b WHERE b.shopId = ?1 AND b.role = ?2 AND b.created >= ?3")
    int countNotifications(Integer shop_id, String role, Date date);

//    int countB2cNotificationByShopIdAndRoleAndCreatedAfter(Integer shop_id, String role, Date date);
}
