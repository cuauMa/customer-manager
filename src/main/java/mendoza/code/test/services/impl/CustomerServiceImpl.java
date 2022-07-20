/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.services.impl;

import mendoza.code.test.models.Address;
import mendoza.code.test.models.Customer;
import mendoza.code.test.repositories.CustomerRepository;
import mendoza.code.test.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomerRepository repository;

    protected  CustomerServiceImpl() {}

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }
     public List<Customer> getCustomers() {
         logger.info("performing getCustomers()");
         return (List<Customer>) repository.findAll();
     }

    @Override
    public Customer getCustomer(Long customerId) {
        logger.info("performing getCustomer()");
        Optional customer = repository.findById(customerId);
        return customer.isPresent() ? (Customer) customer.get() : null;
    }

    @Override
    public void createCustomer(Customer requestBody) {
        logger.info("createCustomer()");
        Customer customer1 = new Customer(requestBody.getCustomerUser(), requestBody.getCustomerName());
        logger.info("Customer1 generated");
        customer1.setAddresses(addCustomerEachAddress(requestBody.getAddresses(), customer1));
        repository.save(customer1);
    }

    @Override
    public void updateCustomer(Long customerId, Customer updateCustomer) {
        logger.info("performing getCustomerById()");
        Optional<Customer> currentCustomer = repository.findById(customerId);
        if (currentCustomer.isPresent()) {
            currentCustomer.get().setCustomerName(updateCustomer.getCustomerName());
            currentCustomer.get().setCustomerUser(updateCustomer.getCustomerUser());
            currentCustomer.get().setAddresses(addCustomerEachAddress(currentCustomer.get().getAddresses(),updateCustomer));
            repository.save(currentCustomer.get());
        }
        else {
            throw new RuntimeException("Customer don't exist");
        }
    }

    @Override
    public void deleteCustomer(Long customerID) {
        logger.info("performing deleteCustomer()");
        Customer customer = getCustomerById(customerID);
        if (customer != null)
            repository.deleteById(customerID);
        else
            throw new RuntimeException("Customer don't exist");
    }

    public Customer getCustomerById(Long customerId) {
         logger.info("performing getCustomerById()");
         Optional<Customer> customer = repository.findById(customerId);
         return customer.isPresent() ? customer.get() : null;
     }

     private Set<Address> addCustomerEachAddress(Set<Address> addresses, Customer customer) {
         Set<Address> addressSet = new HashSet<>();
         logger.info("before to read from Customer1");
         for (Address addr : addresses) {
             Address address = new Address(addr.getAddressTagName(),
                     addr.getAddressLine1(),
                     addr.getAddressLine2(),
                     addr.getZipCode(),
                     addr.getAddressState(),
                     addr.getAddressCountry());
             logger.info("After Address created");
             if (addr.getAddressId() != null)
                address.setAddressId(addr.getAddressId());
             address.setCustomer(customer);
             logger.info("Added Customer1 to Address");
             addressSet.add(address);
         }
         return  addressSet;
     }

}
