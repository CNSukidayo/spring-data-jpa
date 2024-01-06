package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author guyuanjie
 * @date 2024/1/6 16:14
 */
public interface CustomerQueryDSLRepository extends
        PagingAndSortingRepository<Customer, Long>,
        QuerydslPredicateExecutor<Customer> {

}
