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
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class customerManagerServiceImplTest {

    @MockBean
    CustomerRepository customerRepository;

    @Spy
    private CustomerServiceImpl customerServiceSpy;
    
    @Mock
    Customer customer;

    @Test
    public void testFindAll() {
        Customer customer = new Customer("user3", "customer3");
        customerRepository.save(customer);

        CustomerServiceImpl customerManagerService = new CustomerServiceImpl(customerRepository);

        customerRepository.findAll().forEach(x-> System.out.println(x.toString()));

        List<Customer> listCustomer = (List<Customer>) customerManagerService.getCustomers();
        Assertions.assertTrue(!listCustomer.isEmpty());

        Customer lastCustomer = listCustomer.get(listCustomer.size()-1);

        Assertions.assertEquals(customer.getCustomerId(), lastCustomer.getCustomerId());
        Assertions.assertEquals(customer.getCustomerUser(), lastCustomer.getCustomerUser());
        Assertions.assertEquals(customer.getCustomerName(), lastCustomer.getCustomerName());

    }

    @Test
    public void shouldThrowNullPointerException_whenGetCustomerByIdIsCalledWithoutContext() throws Exception {
        //Act
        Customer retrieveCustomer = customerServiceSpy.getCustomerById(5000L);
        //Assert
        assertThat(retrieveCustomer, is(equalTo(customer)));
    }

}
