package com.valencio.hsbc.customer.controller;

import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.service.CustomerAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@ResponseBody
public class CustomerController {

    @Autowired
    private final CustomerAccountService customerAccountService;

    @PostMapping("/customer")
    public Customer create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
        log.debug("Create customer request : {}", customer);
        return customerAccountService.createOrUpdateCustomer(customer);
    }
}
