package com.ghtk.shopservice.webserviceshopreport.service.c2cservice;

import com.ghtk.shopservice.webserviceshopreport.model.C2cNotification;
import com.ghtk.shopservice.webserviceshopreport.repository.C2cRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class C2cNotificationServiceImpl implements C2cNotificationService {
    private final C2cRepository c2cRepository;

    public C2cNotificationServiceImpl(C2cRepository c2cRepository) {
        this.c2cRepository = c2cRepository;
    }

    @Override
    public List<C2cNotification> getMobileNotificationsV3(Integer shop_id, Pageable pageable) {
        return this.c2cRepository.getC2cNotificationByShopId(shop_id, pageable);
    }

    @Override
    public int countNotifications(Integer shop_id, Date date) {
      return c2cRepository.countC2cNotificationByShopIdAndCreatedAfter(shop_id, date);
    }
}
