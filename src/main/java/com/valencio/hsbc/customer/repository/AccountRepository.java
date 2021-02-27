package com.valencio.hsbc.customer.repository;

import com.valencio.hsbc.customer.dto.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
