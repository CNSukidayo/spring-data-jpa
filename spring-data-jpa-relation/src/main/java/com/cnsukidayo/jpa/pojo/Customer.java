package com.cnsukidayo.jpa.pojo;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author guyuanjie
 * @date 2024/1/5 16:50
 */
@Data
@Entity
@Table(name = "tb_customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    /**
     * 单向关联,一对一<br>
     * 通过@JoinColumn设置外键ID,即当前Customer表会有一个account_id字段外键关联Account表的id字段
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Message> messages;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_customer_role",
            joinColumns = {@JoinColumn(name = "c_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")}
    )
    private List<Role> roles;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();

}
