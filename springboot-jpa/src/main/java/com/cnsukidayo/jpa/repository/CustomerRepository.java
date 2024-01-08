package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author sukidayo
 * @date 2024/1/7 10:56
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
