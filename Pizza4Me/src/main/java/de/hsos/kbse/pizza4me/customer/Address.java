package de.hsos.kbse.pizza4me.customer;

import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    
    private String street;
    private String phoneNumber;
    private String zipCode;
    private String city;
    
    public Address(String street, String phoneNumber, String zipCode, String city) {
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
