package com.valencio.hsbc.customer.service;

import com.valencio.hsbc.customer.dao.AccountDAO;
import com.valencio.hsbc.customer.dao.CustomerDAO;
import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.dto.ProductType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountServiceTest {

    LocalDate dob = LocalDate.parse("1993-06-06");
    LocalDate startDate = LocalDate.parse("2020-07-07");
    Customer inputCustomer = new Customer("Valencio", dob, "4 Random St London");
    Customer expectedCustomer = new Customer("Valencio", dob, "4 Random St London");
    Account inputAccount = new Account(startDate, new BigDecimal("10000"), ProductType.CURRENT);
    Account expectedAccount = new Account(startDate, new BigDecimal("10000"), ProductType.CURRENT);
    BigDecimal accountBalance = new BigDecimal("10000");
    @Mock
    private CustomerDAO customerDAO;
    @Mock
    private AccountDAO accountDAO;
    @InjectMocks
    private CustomerAccountServiceImpl customerAccountService;

    @Test
    public void createCustomerTest() {
        expectedCustomer.setId("1234566");
        when(customerDAO.saveCustomer(inputCustomer)).thenReturn(expectedCustomer);
        Assertions.assertThat(customerAccountService.createOrUpdateCustomer(inputCustomer)).isEqualTo(expectedCustomer);
    }

    @Test
    public void createAccountTest() {
        expectedAccount.setId("1234566");
        when(accountDAO.saveAccount(inputAccount)).thenReturn(expectedAccount);
        Assertions.assertThat(customerAccountService.createOrUpdateAccount(inputAccount)).isEqualTo(expectedAccount);
    }

    @Test
    public void getBalanceTest() {
        expectedAccount.setId("1234566");
        when(accountDAO.getAccountBalance(expectedAccount.getId())).thenReturn(accountBalance);
        assertThat(customerAccountService.getAccountBalance(expectedAccount.getId())).isEqualTo(accountBalance);
    }


}
