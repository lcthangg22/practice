package com.ghtk.shopservice.webserviceshopreport.service.b2cservice;

import com.ghtk.shopservice.webserviceshopreport.model.B2cNotification;
import com.ghtk.shopservice.webserviceshopreport.repository.B2cRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class B2cNotificationServiceImpl implements B2cNotificationService {
    private final B2cRepository b2cRepository;

    public B2cNotificationServiceImpl(B2cRepository b2cRepository) {
        this.b2cRepository = b2cRepository;
    }

    @Override
    public List<B2cNotification> getMobileNotificationsV2(Integer shop_id, String role, Date created_from, Date created_to, Integer type, Pageable pageable) {
        return b2cRepository.getMobileNotificationsV2(shop_id, role, created_from, created_to, type, pageable);
    }

    @Override
    public int countNotifications(Integer shop_id, String role, Date date) {
        return b2cRepository.countNotifications(shop_id, role, date);
    }

}
