package de.hsos.kbse.controller;

import de.hsos.kbse.entity.Address;
import de.hsos.kbse.entity.Customer;
import de.hsos.kbse.entity.CustomerOrder;
import de.hsos.kbse.entity.CustomerOrderItem;
import de.hsos.kbse.entity.Login;
import de.hsos.kbse.entity.Pizza;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class CustomerRepository extends Repository<Customer> 
{
    public enum LoginStatus
    {
        VALID,
        INVALID,
        LOCKED
    }
    
    private static final int MAX_LOGINS = 3;
    private static final int LOCK_TIME = 10;
    
    private List<Customer> customers;
    
    @PostConstruct
    public void initialize()
    {
        this.customers = new ArrayList();
    }
    
    public void fillDefault()
    {
        this.persist(new Customer("Max", "Mustermann", "012345/67890", 
                new Address("Musterstrasse", "1", "12345", "Musterstadt"), 
                new Login("mustermann@mail.de", "mustermann")));
        this.persist(new Customer("Susanne", "Meyer", "054321/9870", 
                new Address("Meyerstrasse", "2", "54321", "Meyerstadt"), 
                new Login("meyer@mail.de", "meyer")));
        this.persist(new Customer("Hans", "Schulte", "03284/1165", 
                new Address("Schultestrasse", "3", "96574", "Schultestadt"), 
                new Login("schulte@mail.de", "schulte")));
    }

    public List<Customer> getCustomers() 
    {
        return customers;
    }

    public void setCustomers(List<Customer> customers) 
    {
        this.customers = customers;
    }
    
    public Customer findByEmail(String email)
    {
        TypedQuery<Customer> query = this.em.createNamedQuery("Customer.FindByEmail", Customer.class);
        
        query.setParameter("email", email);
        
        try
        {
            return query.getSingleResult();
        }
        
        catch(Exception e)
        {
            return null;
        }
    }
    
    public LoginStatus customerLogin(Login login)
    {
        Customer c = this.findByEmail(login.getEmail());
        
        if(c == null)
        {
            return LoginStatus.INVALID;
        }
        
        Login l = c.getLogin();
 
        if(!l.isLocked())
        {
            if(l.getPassword().equals(login.getPassword()))
            {
                return LoginStatus.VALID;
            }
            
            else
            {
                l.setNumLogins(l.getNumLogins() + 1);
                
                if(l.getNumLogins() >= CustomerRepository.MAX_LOGINS)
                {
                    this.lockCustomer(c);
                }
            }
        }
        
        else
        {
            Calendar calendar = Calendar.getInstance();
            Timestamp now = new Timestamp(calendar.getTime().getTime());
            Timestamp past = l.getTimestamp();
            long diff = (now.getTime() - past.getTime()) / 1000;
            
            if(diff >= CustomerRepository.LOCK_TIME)
            {
                this.unlockCustomer(c);
                this.customerLogin(login);
            }
            
            return LoginStatus.LOCKED;
        }
        
        return LoginStatus.INVALID;
    }
    
    public void lockCustomer(Customer customer)
    {
        Calendar calendar = Calendar.getInstance();
        
        customer.getLogin().setLocked(true);
        customer.getLogin().setTimestamp(new Timestamp(calendar.getTime().getTime()));
        this.persist(customer);
    }
    
    public void unlockCustomer(Customer customer)
    {
        Login login = customer.getLogin();
        
        login.setLocked(false);
        login.setNumLogins(0);
        
        this.persist(customer);
    }
    
    public void addOrder(Customer customer, PizzaRepository list, PizzaCollection collection)
    {
        List<CustomerOrder> orders = customer.getOrders();
        List<CustomerOrderItem> items = new ArrayList();

        for(Pizza p : list.getPizzaList())
        {
            Pizza pizza = collection.find(p.getId());
            items.add(new CustomerOrderItem(pizza, p.getQuantity()));
        }

        orders.add(new CustomerOrder(items));
        
        this.persist(customer);
    }
}
