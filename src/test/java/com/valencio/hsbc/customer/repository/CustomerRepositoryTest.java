package com.valencio.hsbc.customer.repository;

import com.mongodb.DBObject;
import com.valencio.hsbc.customer.dto.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerRepositoryTest {

    LocalDate dob = LocalDate.parse("1993-06-06");
    Customer customer = new Customer("Valencio", dob, "4 Random St London");

    @Test
    public void createCustomerTest(@Autowired MongoTemplate mongoTemplate,
                                   @Autowired CustomerRepository customerRepository) {
        customerRepository.save(customer);

        List<DBObject> dbObjects = mongoTemplate.findAll(DBObject.class, "customers");
        assertThat(dbObjects.size()).isEqualTo(1);
        assertThat(dbObjects).extracting("name").containsOnly("Valencio");
        assertThat(dbObjects).extracting("dob").containsOnly(Date.valueOf(dob));
        assertThat(dbObjects).extracting("address").containsOnly("4 Random St London");

    }

    @Test
    public void duplicateCustomer_mustNotBeCreated_whenAlreadyExisting(@Autowired MongoTemplate mongoTemplate,
                                                                       @Autowired CustomerRepository customerRepository) {

        customerRepository.save(customer);
        List<DBObject> dbObjects = mongoTemplate.findAll(DBObject.class, "customers");
        assertThat(dbObjects.size()).isEqualTo(1);

        // Attempt to create again & assert
        customerRepository.save(customer);
        dbObjects = mongoTemplate.findAll(DBObject.class, "customers");
        assertThat(dbObjects.size()).isEqualTo(1);

    }

}