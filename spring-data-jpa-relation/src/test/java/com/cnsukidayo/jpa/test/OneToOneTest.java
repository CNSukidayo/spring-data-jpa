package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Account;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author sukidayo
 * @date 2024/1/7 10:48
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testC() {
        // 初始化数据
        Account account = new Account();
        account.setUserName("caixukun");
        Customer customer = new Customer();
        customer.setCustomerName("蔡徐坤");
        customer.setAccount(account);

        customerRepository.save(customer);

    }

    @Test
    public void testR() {
        System.out.println(customerRepository.findById(1L));
    }

    @Test
    @Transactional
    public void testLazy() {
        Optional<Customer> customer = customerRepository.findById(1L);
        System.out.println("=============");
        System.out.println(customer.get()); // 调用toString方法时会调用打印account对象,此时才会去查询account对象
    }

}