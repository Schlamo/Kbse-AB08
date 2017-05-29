package de.hsos.kbse.pizza4me.repository;

import de.hsos.kbse.pizza4me.customer.Customer;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@ApplicationScoped
@Named
public class CustomerRepository {
    @PersistenceContext(name = "CustomerPU")
    private EntityManager em;
    
    private List<Customer> customers;
    
    public CustomerRepository() {
        
    }
    
    @PostConstruct
    private void init() {
        TypedQuery<Customer> query = em.createNamedQuery("getAllCustomers", Customer.class);
        List<Customer> customers = (List<Customer>) query.getResultList();
        this.customers = customers;
    }
    
    public boolean validateUser(String username, String password) {
        for(Customer c: customers) {
            if(c.getLogin().getUsername().equals(username)) {
                if(c.getLogin().getPassword().equals(password)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    
    public Customer getCustomerByUsername(String username) {
        for(Customer c: customers) {
            if(c.getLogin().getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
    
    public void addCustomer(Customer c) {
        this.customers.add(c);
    }
    
}
