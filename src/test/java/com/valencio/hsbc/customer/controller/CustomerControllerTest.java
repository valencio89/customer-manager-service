package com.valencio.hsbc.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valencio.hsbc.customer.dto.Customer;
import com.valencio.hsbc.customer.service.CustomerAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    LocalDate dob = LocalDate.parse("1993-06-06");
    Customer customer = new Customer("Valencio", dob, "4 Random St London");
    Customer createdCustomer = new Customer("Valencio", dob, "4 Random St London");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerAccountService service;

    @Test
    public void test_createCustomer() throws Exception {
        createdCustomer.setId("123456");

        when(service.createOrUpdateCustomer(customer)).thenReturn(createdCustomer);

        this.mockMvc.perform(
                post("/customer")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

    }

    @Test
    public void test_createInvalidCustomer() throws Exception {
        customer.setName("");

        this.mockMvc.perform(
                post("/customer")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is4xxClientError());

    }

}