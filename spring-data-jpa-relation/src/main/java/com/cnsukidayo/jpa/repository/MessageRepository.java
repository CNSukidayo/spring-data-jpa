package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.pojo.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author sukidayo
 * @date 2024/1/7 10:56
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    /**
     * 通过约定方法来实现关联查询,需要通过关联属性进行匹配
     * 但是只能通过id进行匹配
     */
    List<Message> findByCustomer(Customer customer);
}
