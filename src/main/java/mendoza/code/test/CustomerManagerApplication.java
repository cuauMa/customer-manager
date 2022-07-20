/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test;

import mendoza.code.test.models.Address;
import mendoza.code.test.models.Customer;
import mendoza.code.test.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class CustomerManagerApplication {

	private static final Logger log =
			LoggerFactory.getLogger(CustomerManagerApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CustomerManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner checkRepository(CustomerRepository customerRepository) {
		return (args) -> {
			Set<Address> addressSet = new HashSet<>();
			log.info("before to fill Customer1");
			Customer customer1 = new Customer("username1", "customer name 1");
			log.info("Customer1 generated");
			Address address = new Address("mailing post",
					"known street",
					"sunset district","94116","CA","USA"  );
			log.info("After Address created");
			address.setCustomer(customer1);
			log.info("Added Customer1 to Address");
			addressSet.add(address);
			log.info("Address added to Set");
			customer1.setAddresses(addressSet);
			customerRepository.save(customer1);
//			Customer customer2 = new Customer("username2", "customer name 2");
//			customer2.setAddresses(addressSet);
//			customerRepository.save(customer2);

			log.info("Check Customers are stored ");
			log.info("--------------------------");
			customerRepository.findAll().forEach(customer-> log.info("customer = " + customer));
			log.info("");

			Customer customer = customerRepository.findById(1L).get();
			log.info("Check a customer is returned");
			log.info("--------------------------");
			log.info(customer.toString());
			log.info("customer address = " + customer.getAddresses().toString());
			log.info("");
		};
	}


}
