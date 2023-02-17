package com.CustomerReward.Controller;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("create")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
}
