package com.ghtk.shopservice.webserviceshopreport.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "b2c_notis")
@Data
public class B2cNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private Integer id;

    private Integer _id;

    private Integer type;

    private String subject;

    private String addition;

    @NotBlank(message = "shop_id is required")
    private Integer shopId;

    @NotBlank(message = "role is required")
    private String role;

    private Integer read_;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    private Integer deleted;

}
