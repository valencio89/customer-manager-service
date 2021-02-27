package com.valencio.hsbc.customer.dao;

import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.exception.CustomerNotFoundException;
import com.valencio.hsbc.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist - id : " + id));
    }

}
