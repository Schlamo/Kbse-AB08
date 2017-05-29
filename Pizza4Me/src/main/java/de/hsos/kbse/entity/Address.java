package de.hsos.kbse.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class Address extends de.hsos.kbse.entity.Entity
{
    private String street;
    private String streetNumber;
    private String zipCode;
    private String city;
    
    public Address()
    {
        
    }
    
    public Address(String street, String streetNumber, String zipCode, String city)
    {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
    }
    
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street) 
    {
        this.street = street;
    }

    public String getStreetNumber() 
    {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) 
    {
        this.streetNumber = streetNumber;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getCity() 
    {
        return city;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }
}
