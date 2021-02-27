package com.valencio.hsbc.customer.repository;

import com.valencio.hsbc.customer.dto.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
