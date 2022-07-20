/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.services.impl;

import mendoza.code.test.models.Customer;
import mendoza.code.test.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class customerManagerServiceImplTest {

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void testFindAll() {
        Customer customer = new Customer("user3", "customer3");
        customerRepository.save(customer);

        CustomerManagerServiceImpl customerManagerService = new CustomerManagerServiceImpl(customerRepository);

        customerRepository.findAll().forEach(x-> System.out.println(x.toString()));

        List<Customer> listCustomer = (List<Customer>) customerManagerService.getCustomers();
        Assertions.assertTrue(!listCustomer.isEmpty());

        Customer lastCustomer = listCustomer.get(listCustomer.size()-1);

        Assertions.assertEquals(customer.getCustomerId(), lastCustomer.getCustomerId());
        Assertions.assertEquals(customer.getCustomerUser(), lastCustomer.getCustomerUser());
        Assertions.assertEquals(customer.getCustomerName(), lastCustomer.getCustomerName());

    }

}
