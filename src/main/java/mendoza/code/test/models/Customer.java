/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NonNull
    @Column(unique = true)
    private String customerUser;

    @NonNull
    private String customerName;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    private Set<Address> addresses;  // = new HashSet<>();

    public Customer(){}

    public Customer (String customerUser, String customerName) {
        this.customerUser = customerUser;
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @NonNull
    public String getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(@NonNull String customerUser) {
        this.customerUser = customerUser;
    }

    @NonNull
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NonNull String customerName) {
        this.customerName = customerName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUser='" + customerUser + '\'' +
                ", customerName='" + customerName + '\'' +
//                ", addresses=" + addresses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if(!super.equals(o)) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerId != null ? customerId.equals(customer.customerId) : customerId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (getCustomerName() != null ? getCustomerName().hashCode() : 0);
        result = 31 * result + (getCustomerUser() != null ? getCustomerUser().hashCode() : 0);
        result = 31 * result + (getAddresses() != null ? getAddresses().hashCode() : 0);
        result = 31 * result + ((customerId != null) ? customerId.hashCode() : 0);
        return result;
    }
}
