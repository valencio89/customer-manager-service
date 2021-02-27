package com.valencio.hsbc.customer.service;

import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.dto.Customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface CustomerAccountService {
    Customer createOrUpdateCustomer(Customer customer);

    Account createOrUpdateAccount(Account account);

    BigDecimal getAccountBalance(@NotNull @NotEmpty String accountId);

    Customer getCustomer(@NotNull @NotEmpty String id);
}
