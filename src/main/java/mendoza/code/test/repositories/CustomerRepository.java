/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.repositories;

import mendoza.code.test.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustomerUser(String customerUser);

}
