package de.hsos.kbse.pizza4me.boundary;

import de.hsos.kbse.pizza4me.customer.Address;
import de.hsos.kbse.pizza4me.customer.Customer;
import de.hsos.kbse.pizza4me.customer.Login;
import de.hsos.kbse.pizza4me.pizza.Oorder;
import de.hsos.kbse.pizza4me.pizza.Pizza;
import de.hsos.kbse.pizza4me.pizza.PizzaPair;
import de.hsos.kbse.pizza4me.repository.CustomerRepository;
import de.hsos.kbse.pizza4me.repository.PizzaLister;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@Named
public class Boundary implements Serializable{
    
    //@Inject
    //private OrderController orderController;
    
    @Inject
    PizzaLister pizzaLister;
    
    private List<Pizza> pizzas;
    
    @Inject 
    CustomerRepository repo;
    
    Customer customer = new Customer();
    
    private Oorder finalOrder = new Oorder();
    private Oorder order = new Oorder();
    private double price = 0.0;
    private String username;    
    private String password;
    
    public Boundary() {
        System.out.println("BoundaryConstructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("BoundaryInit");
        
        pizzaLister.createPizzas();
        this.customer.setAddress(new Address());
        this.customer.setLogin(new Login());
        
        this.repo.addCustomer(repo.persist(new Customer("Peter", "Peter", 
                new Login("peter", "peter", "peter"), 
                new Address("Peter Strasse 5", "012135179", "12512", "Osnabrooklyn"))));
        System.out.println(pizzaLister.getPizzaList().size());
        if(pizzaLister.getPizzaList().size() != 0) {
            for(Pizza p: pizzaLister.getPizzaList()) {
                this.order.addPair(new PizzaPair(p, 0));
                System.out.println(p.getName());
            }
            System.out.println("Order Size: " + this.order.getPairs().size());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Oorder getFinalOrder() {
        return finalOrder;
    }

    public void setFinalOrder(Oorder finalOrder) {
        this.finalOrder = finalOrder;
    }

    public Oorder getOrder() {
        return order;
    }

    public void setOrder(Oorder order) {
        this.order = order;
    }

    public CustomerRepository getRepo() {
        return repo;
    }

    public void setRepo(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PizzaLister getPizzaLister() {
        return pizzaLister;
    }

    public void setPizzaLister(PizzaLister pizzaLister) {
        this.pizzaLister = pizzaLister;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String order() {
        for(PizzaPair p: order.getPairs()) {
            if(p.getAmount() > 0) {
                finalOrder.addPair(p);
            }
        }
        if(!finalOrder.getPairs().isEmpty()) {
            return "login.xhtml?faces-redirect=true";
        } else {
            return "emptyCart.xhtml?faces-redirect=true";
        }
    }
    
    public String login() {
        if(finalOrder == null) {
            System.out.println("NULL");
        }
        if(repo.validateUser(username, password)) {
            this.customer = repo.getCustomerByUsername(username);
            this.customer.addOrder(finalOrder);
            return "success.xhtml";
        } else {
            return "failure.xhtml";
        }
        
    }
    public String backToIndex() {
            return "index.xhtml?faces-redirect=true";
    }
    
    public void listener() {
        this.price = 0.0;
        for(PizzaPair p: order.getPairs()) {
            System.out.print(p.getPizza().getPrice());
            this.price += p.getPizza().getPrice() * p.getAmount();
        }
    }
}
