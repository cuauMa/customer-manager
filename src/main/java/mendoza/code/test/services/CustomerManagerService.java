/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.services;

import mendoza.code.test.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerManagerService {
    public List<Customer> getCustomers();
}
