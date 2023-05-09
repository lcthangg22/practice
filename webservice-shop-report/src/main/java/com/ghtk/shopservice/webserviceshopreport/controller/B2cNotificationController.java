package com.ghtk.shopservice.webserviceshopreport.controller;

import com.ghtk.shopservice.webserviceshopreport.response.CustomResponse;
import com.ghtk.shopservice.webserviceshopreport.service.b2cservice.B2cNotificationService;
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
@RequestMapping("/api/b2c")
public class B2cNotificationController {
    private final B2cNotificationService b2cNotificationService;

    public B2cNotificationController(B2cNotificationService b2cNotificationService) {
        this.b2cNotificationService = b2cNotificationService;
    }

    @SneakyThrows
    @GetMapping("/notifications/getV2")
    public ResponseEntity<Object> getMobileNotificationsV2(@RequestParam Integer shop_id,
                                                           @RequestParam String role,
                                                           @RequestParam Optional<Integer> pageNo,
                                                           @RequestParam(required = false) Optional<String> created_from,
                                                           @RequestParam(required = false) Optional<String> created_to,
                                                           @RequestParam(required = false) Integer type) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), 5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (created_from.isEmpty()) {
            created_from = Optional.of("1970-01-01");
        }
        if (created_to.isEmpty()) {
            created_to = Optional.of("2970-01-01");
        }
        Date date_from = sdf.parse(created_from.get());
        Date date_to = sdf.parse(created_to.get());
        var b2c = this.b2cNotificationService.getMobileNotificationsV2(shop_id, role, date_from, date_to, type, pageable);
        if (b2c.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return CustomResponse.customResponse(true, b2c);
    }

    @SneakyThrows
    @PostMapping("/notifications/count")
    public ResponseEntity<Object> countNotifications(@RequestParam Optional<Integer> shop_id,
                                                     @RequestParam String role,
                                                     @RequestParam String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (shop_id.isEmpty()) {
            return ResponseEntity.ok().body("shop_id is required");
        }
        int shopId = shop_id.get();
        Date date_start = sdf.parse(date);
        int count = this.b2cNotificationService.countNotifications(shopId, role, date_start);
        Map<String, Object> result = new HashMap<>();
        result.put("shop_id", shop_id);
        result.put("notifications_count", count);
        return CustomResponse.customResponse(true, result);
    }
}
