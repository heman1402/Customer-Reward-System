package com.CustomerReward.Service.Impl;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Repository.CustomerRepository;
import com.CustomerReward.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
}
