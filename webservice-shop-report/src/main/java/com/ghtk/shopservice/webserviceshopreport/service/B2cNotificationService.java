package com.ghtk.shopservice.webserviceshopreport.service;

import com.ghtk.shopservice.webserviceshopreport.model.B2cNotification;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface B2cNotificationService {
    List<B2cNotification> getMobileNotificationsV2(Integer shop_id, String role, Date created_from, Date created_to, Integer type, Integer group_id, Pageable pageable);
}
