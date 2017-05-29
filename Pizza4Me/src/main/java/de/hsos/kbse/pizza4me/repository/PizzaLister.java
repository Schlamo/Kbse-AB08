package de.hsos.kbse.pizza4me.repository;

import de.hsos.kbse.pizza4me.pizza.Pizza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named
@ApplicationScoped
public class PizzaLister implements Serializable{
    @PersistenceContext(unitName = "CustomerPU")
    protected EntityManager em;
    
    private List<Pizza> pizzas = new ArrayList();
    
    @PostConstruct
    public void init() {
        System.out.println("PizzaLister init()");
        
        Pizza p = new Pizza( 1, "Pizza Margherita", 4.5);
    }

    public PizzaLister() {
        System.out.println("PizzaLister Constructor");
    }
    
    public List<Pizza> getPizzaList() {
        System.out.println("getPizzaList");
        return this.pizzas;
    }
    
    private Pizza persist(Pizza p) {
        try {
            this.em.persist(p);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
