package com.ghtk.shopservice.webserviceshopreport.service.b2cservice;

import com.ghtk.shopservice.webserviceshopreport.model.B2cNotification;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

public interface B2cNotificationService {
    List<B2cNotification> getMobileNotificationsV2(Integer shop_id, String role, Date created_from, Date created_to, Integer type, Pageable pageable);

    int countNotifications(Integer shop_id, String role, Date date);
}
