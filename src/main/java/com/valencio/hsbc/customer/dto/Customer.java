package com.valencio.hsbc.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "customers")
@Data
@NoArgsConstructor
@CompoundIndexes({@CompoundIndex(name = "unique_cust", unique = true, def = "{'name': 1, 'dob': 1, 'address': 1}")})
public class Customer {

    private String id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;

    @NotNull
    @NotEmpty
    private String address;

    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, LocalDate dob, String address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public void addAccount(Account account) {

        accounts.add(account);
    }
}
