package com.ghtk.shopservice.webserviceshopreport.service.c2cservice;

import com.ghtk.shopservice.webserviceshopreport.model.C2cNotification;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface C2cNotificationService {
    List<C2cNotification> getMobileNotificationsV3(Integer shop_id, Pageable pageable);

    int countNotifications(Integer shop_id, Date date);

}
