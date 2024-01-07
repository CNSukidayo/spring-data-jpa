package com.cnsukidayo.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sukidayo
 * @date 2024/1/7 15:32
 */
@Data
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String rName;

}
