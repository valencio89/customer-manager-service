package com.valencio.hsbc.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valencio.hsbc.customer.dto.Account;
import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.dto.ProductType;
import com.valencio.hsbc.customer.service.CustomerAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    LocalDate dob = LocalDate.parse("1993-06-06");
    LocalDate startDate = LocalDate.parse("2020-02-27");
    Customer customer = new Customer("Valencio", dob, "4 Random St London");
    Account account = new Account(startDate, new BigDecimal("10000"), ProductType.CURRENT);
    Customer createdCustomer = new Customer("Valencio", dob, "4 Random St London");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerAccountService service;

    @Test
    public void test_createAccount() throws Exception {
        when(service.getCustomer("custId123456")).thenReturn(customer);

        this.mockMvc.perform(
                post("/account/custId123456")
                        .content(objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

    }

    @Test
    public void test_getBalance() throws Exception {
        when(service.getAccountBalance("123456")).thenReturn(new BigDecimal("10000"));

        this.mockMvc.perform(
                get("/balance/123456"))
                .andExpect(status().isOk())
                .andExpect(content().string("10000"));

    }

    @Test
    public void test_createAccount_ValidationFail_when_negativeBalance() throws Exception {
        when(service.getCustomer("custId123456")).thenReturn(customer);
        account.setBalance(new BigDecimal("-1"));

        this.mockMvc.perform(
                post("/account/custId123456")
                        .content(objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is4xxClientError());

    }

}