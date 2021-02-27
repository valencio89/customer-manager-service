package com.valencio.hsbc.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Account {

    private String id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate startDate;

    @PositiveOrZero
    private BigDecimal balance;

    private List<String> transactions;

    @NotNull
    private ProductType productType;

    public Account(@NotNull @NotEmpty LocalDate startDate, BigDecimal balance, @NotNull ProductType productType) {
        this.startDate = startDate;
        this.balance = balance;
        this.productType = productType;
    }

}
