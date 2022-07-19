/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.services.impl;

import mendoza.code.test.models.Customer;
import mendoza.code.test.repositories.CustomerRepository;
import mendoza.code.test.services.CustomerManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerManagerServiceImpl implements CustomerManagerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CustomerRepository repository;

    public CustomerManagerServiceImpl(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }
     public List<Customer> getCustomers() {
         logger.info("performing getCustomers()");
         return (List<Customer>) repository.findAll();
     }
}
