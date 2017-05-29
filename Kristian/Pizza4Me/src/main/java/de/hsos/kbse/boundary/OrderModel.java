package de.hsos.kbse.boundary;

import de.hsos.kbse.controller.CustomerRepository;
import de.hsos.kbse.controller.CustomerRepository.LoginStatus;
import de.hsos.kbse.controller.PizzaCollection;
import de.hsos.kbse.controller.PizzaRepository;
import de.hsos.kbse.entity.Address;
import de.hsos.kbse.entity.Customer;
import de.hsos.kbse.entity.Login;
import de.hsos.kbse.entity.Pizza;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Stateless
public class OrderModel implements Serializable
{
    @Inject
    private PizzaCollection pizzaCollection;
    
    @Inject
    private PizzaRepository pizzaList;
    
    @Inject 
    private CustomerRepository customers;
    
    private Customer customer;
    
    private boolean loggedIn;
    private boolean firstLogin;

    public String callOrderForm()
    {
        if(this.pizzaCollection.getSize() <= 0)
        {
            this.pizzaCollection.fillDefault();
            this.customers.fillDefault();
        }
        
        return "order";
    }
    
    public String callLoginForm()
    {
        return "login";
    }
    
    public String callIndex()
    {
        this.pizzaList.endConversation();
        this.initialize();
        
        return "index";
    }
    
    public void addPizza(Pizza pizza)
    {
        this.pizzaList.add(pizza);
    }
    
    public String doLogin()
    {
        Login login = this.customer.getLogin();
        LoginStatus status = this.customers.customerLogin(login);
        
        this.loggedIn = status == LoginStatus.VALID;
        login.setLocked(status == LoginStatus.LOCKED);
        
        if(this.loggedIn)
        {
            this.customer = this.customers.findByEmail(login.getEmail());
            this.customers.addOrder(this.customer, this.pizzaList, this.pizzaCollection);
        }
        
        this.firstLogin = false;
        
        if(this.loggedIn)
        {
            return "summary";
        }
        
        else
        {
            return null;
        }
    }

    public PizzaCollection getPizzaCollection() 
    {
        return pizzaCollection;
    }

    public void setPizzaCollection(PizzaCollection pizzaCollection) 
    {
        this.pizzaCollection = pizzaCollection;
    }

    public PizzaRepository getPizzaList() 
    {
        return pizzaList;
    }

    public void setPizzaList(PizzaRepository pizzaList) 
    {
        this.pizzaList = pizzaList;
    }
    
    @PostConstruct
    public void initialize()
    {
        this.customer = new Customer();
        this.customer.setLogin(new Login());
        this.customer.setAddress(new Address());
        this.firstLogin = true;
    }

    public CustomerRepository getCustomers() 
    {
        return customers;
    }

    public void setCustomers(CustomerRepository customers) 
    {
        this.customers = customers;
    }
    
    public boolean isLoggedIn() 
    {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) 
    {
        this.loggedIn = loggedIn;
    }

    public Customer getCustomer() 
    {
        return customer;
    }

    public void setCustomer(Customer customer) 
    {
        this.customer = customer;
    }

    public boolean isFirstLogin() 
    {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) 
    {
        this.firstLogin = firstLogin;
    }
}
