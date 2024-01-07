package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.pojo.Role;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sukidayo
 * @date 2024/1/7 15:43
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testC() {
        List<Role> roles = new ArrayList<>();
        Role role0 = new Role();
        role0.setRName("超级管理员");
        Role role1 = new Role();
        role1.setRName("商品管理员");
        Customer customer = new Customer();
        roles.add(role0);
        roles.add(role1);
        customer.setRoles(roles);

        customerRepository.save(customer);
    }

    @Test
    @Transactional(readOnly = true)
    public void testR() {
        System.out.println(customerRepository.findById(10L));
    }

    @Test
    @Transactional(readOnly = true)
    @Commit
    public void testD() {
        Optional<Customer> customer = customerRepository.findById(10L);
        customerRepository.delete(customer.get());
    }



}
