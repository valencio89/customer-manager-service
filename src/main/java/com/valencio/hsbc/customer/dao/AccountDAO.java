package com.valencio.hsbc.customer.dao;

import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.exception.AccountNotFoundException;
import com.valencio.hsbc.customer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountDAO {
    @Autowired
    AccountRepository accountRepository;


    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public BigDecimal getAccountBalance(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account does not exist - id : " + id))
                .getBalance();
    }

}
