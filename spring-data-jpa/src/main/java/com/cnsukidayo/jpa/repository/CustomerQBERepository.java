package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author guyuanjie
 * @date 2024/1/6 16:14
 */
public interface CustomerQBERepository extends
        PagingAndSortingRepository<Customer, Long>,
        QueryByExampleExecutor<Customer> {

}
