/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addressTagName;
    private String addressLine1;
    private String addressLine2;
    @NonNull
    @Size(max=5)
    private String zipCode;
    @NonNull
    private String addressState;
    @NonNull
    private String addressCountry;

    @ManyToOne //(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIncludeProperties("addressesList")
    private Customer customer;

    public Address() {}

    public Address(String addressTagName, String addressLine1, String addressLine2, String zipCode, String addressState, String addressCountry) {
        this.addressTagName = addressTagName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.zipCode = zipCode;
        this.addressState = addressState;
        this.addressCountry = addressCountry;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressTagName() {
        return addressTagName;
    }

    public void setAddressTagName(String addressTagName) {
        this.addressTagName = addressTagName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @NonNull
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NonNull String zipCode) {
        this.zipCode = zipCode;
    }

    @NonNull
    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(@NonNull String addressState) {
        this.addressState = addressState;
    }

    @NonNull
    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(@NonNull String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressTagName='" + addressTagName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", addressState='" + addressState + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;

        Address address = (Address) o;

        if (getCustomer() != null ? !getCustomer().equals(address.getCustomer()) : address.getCustomer() != null)
            return false;
        if (getAddressLine1() != null ? !getAddressLine1().equals(address.getAddressLine1()) : address.getAddressLine1() != null)
            return false;
        return getAddressTagName() != null ? getAddressTagName().equals(address.getAddressTagName()) : address.getAddressTagName() ==null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getAddressLine1() != null ? getAddressLine1().hashCode() : 0);
        result = 31 * result + (getAddressTagName() != null ? getAddressTagName().hashCode() : 0);
        return result;
    }
}
