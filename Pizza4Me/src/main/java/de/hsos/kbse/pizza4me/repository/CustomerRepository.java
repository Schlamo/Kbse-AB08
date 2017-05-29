package de.hsos.kbse.pizza4me.repository;

import de.hsos.kbse.pizza4me.customer.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@ApplicationScoped
@Named
public class CustomerRepository {
    @PersistenceContext(unitName = "CustomerPU")
    private EntityManager em;
    
    private List<Customer> customers;
    private Map<String,Long> locked = new HashMap<>();
    private Map<String,Integer> attempt = new HashMap<>();
    
    public CustomerRepository() {
        
    }
    
    @PostConstruct
    private void init() {
        TypedQuery<Customer> query = em.createNamedQuery("getAllCustomers", Customer.class);
        List<Customer> customers = (List<Customer>) query.getResultList();
        this.customers = customers;
    }
    
    public boolean validateUser(String username, String password) {
        System.out.println(customers.size());
        for(Customer c: customers) {
            if(c.getLogin().getUsername().equals(username)) {
                if(!isLocked(username) && c.getLogin().getPassword().equals(password)) {
                    return true;
                }
            } 
        }
        
        System.out.println("################## user: " + username + " attempt " + attempt.get(username));
        attempt.put(username, attempt.get(username)!=null ? attempt.get(username) + 1 : 1);
        if(attempt.get(username)!=null && attempt.get(username) > 2){
            locked.put(username, System.currentTimeMillis());
            System.out.println("################## user: " + username + " locked");
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
    
    public Customer persist(Customer c) {
        try {
            this.em.persist(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public boolean isLocked(String username){
        if(locked.get(username)!=null)  
            System.out.println("################## user: " + username + " diff: " + (System.currentTimeMillis()-locked.get(username)));
        if(locked.get(username)!=null && (System.currentTimeMillis()-locked.get(username))<10000)
            return true;
        else if(locked.get(username)!=null && (System.currentTimeMillis()-locked.get(username))>=10000){
            System.out.println("################## user: " + username + " released");
            attempt.remove(username);
            locked.remove(username);
        }
        return false;
            
    }
}
