package com.ghtk.shopservice.webserviceshopreport.response;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
public class CustomResponse {

    public static ResponseEntity<Object> customResponse(Boolean isSuccess, Object response) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", isSuccess);
        map.put("data", response);

        return ResponseEntity.ok().body(map);
    }
}
