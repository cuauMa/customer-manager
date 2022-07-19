/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.models;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @NonNull
    private String customerUser;
    @NonNull
    private String customerName;

    protected Customer(){}

    public Customer (String customerUser, String customerName) {
        this.customerUser = customerUser;
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUser='" + customerUser + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
