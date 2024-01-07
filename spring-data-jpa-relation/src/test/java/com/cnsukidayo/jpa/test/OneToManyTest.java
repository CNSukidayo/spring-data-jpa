package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.pojo.Message;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sukidayo
 * @date 2024/1/7 14:12
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToManyTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testC() {
        List<Message> messages = new ArrayList<>();
        Message message0 = new Message();
        message0.setInfo("鸡你");
        Message message1 = new Message();
        message1.setInfo("太美");
        messages.add(message0);
        messages.add(message1);
        Customer customer = new Customer();
        customer.setCustomerName("丁真");
        customer.setMessages(messages);
        customerRepository.save(customer);
    }

    @Test
    @Transactional(readOnly = true)
    public void testR() {
        Optional<Customer> result = customerRepository.findById(1L);
        System.out.println("=========");
        System.out.println(result);
    }

}
