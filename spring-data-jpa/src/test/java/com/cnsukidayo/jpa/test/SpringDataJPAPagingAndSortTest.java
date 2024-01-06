package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guyuanjie
 * @date 2024/1/6 13:38
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJPAPagingAndSortTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testPaging() {
        System.out.println(customerRepository.findAll(PageRequest.of(0, 2)).getContent());
    }

    @Test
    public void testSort() {
        Sort.by("customer_id")
                .descending()
                .and(Sort.by("customer_address"));
    }

    @Test
    public void testTypeSafe() {
        Sort.TypedSort<Customer> sortType = Sort.sort(Customer.class);
        Sort sort = sortType.by(Customer::getCustomerId)
                .ascending()
                .and(
                        sortType.by(Customer::getCustomerAddress)
                                .descending()
                );
    }


}