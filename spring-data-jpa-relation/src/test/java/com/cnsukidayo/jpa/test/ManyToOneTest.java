package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.pojo.Message;
import com.cnsukidayo.jpa.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sukidayo
 * @date 2024/1/7 14:57
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToOneTest {

    private MessageRepository messageRepository;

    @Test
    public void testC() {
        Customer customer = new Customer();
        customer.setCustomerName("马嘉祺");
        List<Message> list = new ArrayList<>();
        Message message0 = new Message();
        message0.setInfo("小");
        message0.setCustomer(customer);
        Message message1 = new Message();
        message1.setInfo("气球");
        message1.setCustomer(customer);
        list.add(message0);
        list.add(message1);
        customer.setMessages(list);
        messageRepository.saveAll(list);
    }

    @Test
    public void testR() {
        // 这里设置setCustomerName方法是没用的,因为findByCustomer只会通过customerId来进行查询
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("xxxx");
        List<Message> messages = messageRepository.findByCustomer(customer);
        // 这里可能会报错留个心眼,循环调用toString
        System.out.println(messages);
    }

}
