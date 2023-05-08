//package com.ghtk.shopservice.webserviceshopreport.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.util.Date;
//
//@Entity
//@Table(name = "c2c_notis")
//@Data
//public class C2cNotification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "_id")
//    private Long id;
//
//    private Number type;
//
//    private String subject;
//
//    private String addition;
//
//    @Column(name = "object_id")
//    private String shop_id;
//
//    @NotBlank
//    @Column(name = "object_type")
//    private String role;
//
//    private Number read;
//
//    private Date created;
//
//    private Number deleted;
//}
