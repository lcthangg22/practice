package com.ghtk.shopservice.webserviceshopreport.config;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    private final Integer SHOP_NOTIFICATION_TYPE_IMPORTANT = 0;
    private final Integer SHOP_NOTIFICATION_TYPE_FEEDBACK = 1;
    private final Integer SHOP_NOTIFICATION_TYPE_PICKUP = 2;
    private final Integer SHOP_NOTIFICATION_TYPE_DELIVER = 3;
    private final Integer SHOP_NOTIFICATION_TYPE_RETURN = 4;
    private final Integer SHOP_NOTIFICATION_TYPE_PAYMENT = 5;
    private final Integer SHOP_NOTIFICATION_TYPE_NEWS = 6;
    private final Integer SHOP_NOTIFICATION_TYPE_CHANGE_PACKAGE = 7;

    @Bean
    public Map<String, Object> important() {
        Map<String, Object> important = new HashMap<>();
        important.put("id", this.SHOP_NOTIFICATION_TYPE_IMPORTANT);
        important.put("title", "Quan trọng");
        important.put("types", new int[]{13, 14, 8, 9, 10, 11, 16, 17, 18});
        return important;
    }

    @Bean
    public Map<String, Object> feedback() {
        Map<String, Object> feedback = new HashMap<>();
        feedback.put("id", this.SHOP_NOTIFICATION_TYPE_FEEDBACK);
        feedback.put("title", "Yêu cầu");
        feedback.put("types", new int[]{0});
        return feedback;
    }

    @Bean
    public Map<String, Object> pickup() {
        Map<String, Object> pickup = new HashMap<>();
        pickup.put("id", this.SHOP_NOTIFICATION_TYPE_PICKUP);
        pickup.put("title", "Lấy hàng");
        pickup.put("types", new int[]{1, 4, 12, 13, 14});
        return pickup;
    }

    @Bean
    public Map<String, Object> deliver() {
        Map<String, Object> deliver = new HashMap<>();
        deliver.put("id", this.SHOP_NOTIFICATION_TYPE_DELIVER);
        deliver.put("title", "Yêu cầu");
        deliver.put("types", new int[]{5, 8, 9, 10, 11});
        return deliver;
    }

    @Bean
    public Map<String, Object> returns() {
        Map<String, Object> returns = new HashMap<>();
        returns.put("id", this.SHOP_NOTIFICATION_TYPE_RETURN);
        returns.put("title", "Trả hàng");
        returns.put("types", new int[]{15, 16});
        return returns;
    }

    @Bean
    public Map<String, Object> payment() {
        Map<String, Object> payment = new HashMap<>();
        payment.put("id", this.SHOP_NOTIFICATION_TYPE_PAYMENT);
        payment.put("title", "Đối soát");
        payment.put("types", new int[]{3});
        return payment;
    }

    @Bean
    public Map<String, Object> news() {
        Map<String, Object> news = new HashMap<>();
        news.put("id", this.SHOP_NOTIFICATION_TYPE_NEWS);
        news.put("title", "Tin tức");
        news.put("types", new int[]{});
        return news;
    }

    @Bean
    public Map<String, Object> changePackage() {
        Map<String, Object> changePackage = new HashMap<>();
        changePackage.put("id", this.SHOP_NOTIFICATION_TYPE_CHANGE_PACKAGE);
        changePackage.put("title", "Thay đổi đơn hàng");
        changePackage.put("types", new int[]{17, 18, 21});
        return changePackage;
    }
}

