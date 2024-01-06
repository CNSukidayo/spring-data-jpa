package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerQBERepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author guyuanjie
 * @date 2024/1/6 16:22
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QBETest {
    @Autowired
    private CustomerQBERepository customerQBERepository;

    @Test
    public void testR() {
        // 通过这种方式就可以做到类似mybatis一样的效果
        // 只查询前端传过来的字段,对于对象为null的字段不进行查询
        // 再次说明该方法只能用于查询一个对象,不能嵌套查询
        Customer customer = new Customer();
        customer.setCustomerName("吴亦凡");
        customer.setCustomerAddress("朝阳看守所");
        Example<Customer> example = Example.of(customer);
        List<Customer> result = (List<Customer>) customerQBERepository.findAll(example);
        System.out.println(result);
    }

    /**
     * 通过匹配器来进行条件的限制
     * 对应上述讲的start/contains/ends/regex这四个条件
     */
    @Test
    public void testMatch() {
        Customer customer = new Customer();
        customer.setCustomerName("吴亦凡");
        customer.setCustomerAddress("BEIJING");
        // 通过该匹配器表示在查询的时候忽略customerName这个条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("customerName")    // 忽略customerName条件
                .withIgnoreCase("customerAddress") // 忽略customerAddress属性的大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("customerName", ExampleMatcher.GenericPropertyMatcher::endsWith); //对单个属性进行匹配,第二个参数是lambda表达式
        // 把exampleMatcher匹配器传入到Example中
        Example<Customer> example = Example.of(customer, exampleMatcher);
        List<Customer> result = (List<Customer>) customerQBERepository.findAll(example);
    }

}
