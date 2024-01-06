package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guyuanjie
 * @date 2024/1/6 14:55
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JPQLTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testR() {
        Customer customer = customerRepository.findCustomerByCustomerNameWithOrder("蔡徐坤");
        System.out.println(customer);
    }

}
