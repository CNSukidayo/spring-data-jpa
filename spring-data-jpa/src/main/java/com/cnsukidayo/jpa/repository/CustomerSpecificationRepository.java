package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author guyuanjie
 * @date 2024/1/6 16:14
 */
public interface CustomerSpecificationRepository extends
        PagingAndSortingRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

}
