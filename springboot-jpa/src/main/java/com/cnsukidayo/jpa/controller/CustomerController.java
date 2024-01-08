package com.cnsukidayo.jpa.controller;

import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guyuanjie
 * @date 2024/1/8 14:54
 */
@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("get")
    public List<Customer> get() {
        return (List<Customer>) customerRepository.findAll();
    }

}
