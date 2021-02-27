package com.valencio.hsbc.customer.controller;

import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.service.CustomerAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;

@Slf4j
@RestController
@AllArgsConstructor
@ResponseBody
public class AccountController {

    @Autowired
    private final CustomerAccountService customerAccountService;

    @Transactional
    @PostMapping("/account/{customerId}")
    public Account create(@RequestBody @Valid Account account, @PathVariable String customerId,
                          HttpServletResponse response) {
        log.debug("Create Account request - Id : {} | Account : {}", customerId, account);
        Customer customer = customerAccountService.getCustomer(customerId);
        customer.addAccount(account);
        Account savedAccount = customerAccountService.createOrUpdateAccount(account);
        customerAccountService.createOrUpdateCustomer(customer);
        return savedAccount;
    }

    @GetMapping("/balance/{accountId}")
    public BigDecimal getBalance(@PathVariable String accountId) {
        log.debug("Balance request - account id : {}" + accountId);
        return customerAccountService.getAccountBalance(accountId);
    }

}
