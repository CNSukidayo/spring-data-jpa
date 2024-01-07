package com.cnsukidayo.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sukidayo
 * @date 2024/1/7 13:58
 */
@Data
@Entity
@Table(name = "tb_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "message_info")
    private String info;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
