package com.cnsukidayo.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author guyuanjie
 * @date 2024/1/5 16:50
 */
@Data
@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

}
