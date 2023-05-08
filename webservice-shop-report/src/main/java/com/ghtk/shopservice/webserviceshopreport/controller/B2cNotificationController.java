package com.ghtk.shopservice.webserviceshopreport.controller;

import com.ghtk.shopservice.webserviceshopreport.response.CustomResponse;
import com.ghtk.shopservice.webserviceshopreport.service.B2cNotificationService;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                                                           @RequestParam(required = false) String created_from,
                                                           @RequestParam(required = false) String created_to,
                                                           @RequestParam(required = false) Integer group_id,
                                                           @RequestParam(required = false) Integer type) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), 5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date_from = sdf.parse(created_from);
        Date date_to = sdf.parse(created_to);
        var b2c = this.b2cNotificationService.getMobileNotificationsV2(shop_id, role, date_from, date_to, type, group_id, pageable);
        if (b2c.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return CustomResponse.customResponse(true, b2c);
    }
}
