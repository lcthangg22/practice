package com.ghtk.shopservice.webserviceshopreport.controller;

import com.ghtk.shopservice.webserviceshopreport.response.CustomResponse;
import com.ghtk.shopservice.webserviceshopreport.service.c2cservice.C2cNotificationService;
import com.ghtk.shopservice.webserviceshopreport.validator.CustomValidator;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/c2c")
public class C2cNotificationController {
    private final C2cNotificationService c2cNotificationService;

    public C2cNotificationController(C2cNotificationService c2cNotificationService) {
        this.c2cNotificationService = c2cNotificationService;
    }

    @GetMapping("/notifications/getV3")
    public ResponseEntity<Object> getMobileNotificationsV3(@RequestParam Integer shop_id,
                                                           @RequestParam(required = false) Optional<Integer> pageNo) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), 5);
        var ret = this.c2cNotificationService.getMobileNotificationsV3(shop_id, pageable);
        return CustomResponse.customResponse(true, ret);
    }

    @SneakyThrows
    @PostMapping("notifications/count")
    public ResponseEntity<Object> countNotifications(@RequestParam Optional<Integer> shop_id,
                                                     @RequestParam String date) {
        if (shop_id.isEmpty()) {
            return CustomValidator.customValidator(false, "shop is required");
        }
        int shopId = shop_id.get();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date_start = sdf.parse(date);
        int count = this.c2cNotificationService.countNotifications(shopId, date_start);
        Map<String, Object> result = new HashMap<>();
        result.put("shop_id", shop_id);
        result.put("notifications_count", count);

        return CustomResponse.customResponse(true, result);
    }
}
