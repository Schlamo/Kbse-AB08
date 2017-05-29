package de.hsos.kbse.entity;

import java.util.List;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.FIELD)
@Vetoed
@NamedQuery(name = "Customer.FindByEmail", 
        query = "select distinct c from Customer c where c.login.email=:email")
public class Customer extends de.hsos.kbse.entity.Entity
{ 
    private String fistName;
    private String lastName;
    private String phoneNumber;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="ADDRESS_ID")
    private Address address;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="LOGIN_ID")
    private Login login;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CustomerOrder> orders;
    
    public Customer()
    {
        
    }
    
    public Customer(String firstName, String lastName, String phoneNumber, 
            Address address, Login login)
    {
        this.fistName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.login = login;
    }

    public String getFistName() 
    {
        return fistName;
    }

    public void setFistName(String fistName) 
    {
        this.fistName = fistName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    public Address getAddress() 
    {
        return address;
    }

    public void setAddress(Address address) 
    {
        this.address = address;
    }

    public Login getLogin() 
    {
        return login;
    }

    public void setLogin(Login login) 
    {
        this.login = login;
    }

    public List<CustomerOrder> getOrders() 
    {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) 
    {
        this.orders = orders;
    }
}
