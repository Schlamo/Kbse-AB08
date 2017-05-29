package de.hsos.kbse.pizza4me.customer;

import de.hsos.kbse.pizza4me.pizza.Oorder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.FIELD)
@Vetoed
@NamedQueries ({
    @NamedQuery(name = "getAllCustomers", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "getCustomerByUsername", query = "SELECT c FROM Customer c WHERE c.login.username = :username")
})
public class Customer implements Serializable{
    private String firstName;
    private String lastName;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="LOGIN_ID")
    private Login login;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="ADDRESS_ID")    
    private Address address;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Oorder> orders;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void addOrder(Oorder order) {
        this.orders.add(order);
    }

    public List<Oorder> getOrders() {
        return orders;
    }

    public void setOrders(List<Oorder> orders) {
        this.orders = orders;
    }
    
    public Customer() {
        
    }

    public Customer(String firstName, String lastName, Login login, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.address = address;
        this.orders = new ArrayList();
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
