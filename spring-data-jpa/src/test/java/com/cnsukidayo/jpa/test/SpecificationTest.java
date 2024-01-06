package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerSpecificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guyuanjie
 * @date 2024/1/6 16:54
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationTest {
    @Autowired
    private CustomerSpecificationRepository customerSpecificationRepository;

    @Test
    public void testR() {
        List<Customer> result = customerSpecificationRepository.findAll((root, query, criteriaBuilder) -> {
            /*
            root:用户获取需要查询的列;得到的返回值的泛型是该属性对应的泛型(需要手动设置,默认是Object)
            criteriaBuilder:构造查询条件
            query:组合 orderBy,where
             */
            Path<Long> customerId = root.get("customerId");
            Path<String> customerName = root.get("customerName");
            Path<String> customerAddress = root.get("customerAddress");
            // 通过criteriaBuilder来设置查询条件;args0:为那个字段设置条件;args1:值
            criteriaBuilder.equal(customerAddress, "BEIJING");
            // 返回最后一个生成的predicate即可
            Predicate predicate = criteriaBuilder.greaterThan(customerId, 0L);
            return predicate;
        });
        System.out.println(result);
    }

    @Test
    public void testSearch() {
        // 模拟一个真实的查询效果,假设这里的Customer是前端传过来的
        Customer customer = new Customer();
        customer.setCustomerName("蔡徐坤");

        List<Customer> result = customerSpecificationRepository.findAll((root, query, criteriaBuilder) -> {
            Path<Long> customerId = root.get("customerId");
            Path<String> customerName = root.get("customerName");
            Path<String> customerAddress = root.get("customerAddress");
            // 需要一个List来保存所有的动态条件
            List<Predicate> list = new ArrayList<>();
            if (customer.getCustomerId() != null) {
                list.add(criteriaBuilder.equal(customerId, customer.getCustomerId()));
            }
            if (StringUtils.hasText(customer.getCustomerName())) {
                list.add(criteriaBuilder.equal(customerName, customer.getCustomerName()));
            }
            if (StringUtils.hasText(customer.getCustomerAddress())) {
                list.add(criteriaBuilder.equal(customerAddress, customer.getCustomerAddress()));
            }
            Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            return predicate;
        });
        System.out.println(result);
    }

    @Test
    public void testSearchAndSort() {
        // 模拟一个真实的查询效果,假设这里的Customer是前端传过来的
        Customer customer = new Customer();
        customer.setCustomerName("蔡徐坤");

        List<Customer> result = customerSpecificationRepository.findAll((root, query, criteriaBuilder) -> {
            Path<Long> customerId = root.get("customerId");
            Path<String> customerName = root.get("customerName");
            Path<String> customerAddress = root.get("customerAddress");
            // 需要一个List来保存所有的动态条件
            List<Predicate> list = new ArrayList<>();
            if (customer.getCustomerId() != null) {
                list.add(criteriaBuilder.equal(customerId, customer.getCustomerId()));
            }
            if (StringUtils.hasText(customer.getCustomerName())) {
                list.add(criteriaBuilder.equal(customerName, customer.getCustomerName()));
            }
            if (StringUtils.hasText(customer.getCustomerAddress())) {
                list.add(criteriaBuilder.equal(customerAddress, customer.getCustomerAddress()));
            }
            Predicate[] where = list.toArray(new Predicate[list.size()]);
            // 如果需要按某些字段排序
            Order desc = criteriaBuilder.desc(customerId);
            return query.where(where).orderBy(desc).getRestriction();
        });
        System.out.println(result);
    }

}
