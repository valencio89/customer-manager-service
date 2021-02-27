package com.valencio.hsbc.customer.service;

import com.valencio.hsbc.customer.dao.AccountDAO;
import com.valencio.hsbc.customer.dao.CustomerDAO;
import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Customer createOrUpdateCustomer(Customer customer) {
        return customerDAO.saveCustomer(customer);
    }

    @Override
    public Account createOrUpdateAccount(Account account) {
        return accountDAO.saveAccount(account);
    }

    @Override
    public BigDecimal getAccountBalance(@NotNull @NotEmpty String accountId) {
        return accountDAO.getAccountBalance(accountId);
    }

    @Override
    public Customer getCustomer(@NotNull @NotEmpty String id) {
        return customerDAO.getCustomer(id);
    }


}
