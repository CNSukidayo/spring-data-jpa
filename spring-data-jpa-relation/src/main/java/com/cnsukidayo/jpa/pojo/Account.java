package com.cnsukidayo.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sukidayo
 * @date 2024/1/6 21:08
 */
@Data
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_user_name")
    private String userName;

    @Column(name = "account_password")
    private String password;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
