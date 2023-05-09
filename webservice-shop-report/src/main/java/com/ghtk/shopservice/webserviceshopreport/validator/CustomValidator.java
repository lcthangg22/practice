package com.ghtk.shopservice.webserviceshopreport.validator;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class CustomValidator {
    public static ResponseEntity<Object> customValidator(Boolean isSuccess, Object response) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", isSuccess);
        map.put("message", response);

        return ResponseEntity.ok().body(map);
    }
}
