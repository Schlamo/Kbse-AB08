package de.hsos.kbse.pizza4me.repository;

import de.hsos.kbse.pizza4me.pizza.Pizza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class PizzaLister implements Serializable{
    @PersistenceContext(unitName = "CustomerPU")
    protected EntityManager em;
    
    private List<Pizza> pizzaList = new ArrayList();
    
    @PostConstruct
    public void init() {
        TypedQuery<Pizza> query = this.em.createNamedQuery("findAllPizzas", Pizza.class);
        this.pizzaList = (List<Pizza>)query.getResultList();
    }

    public PizzaLister() {
        this.pizzaList.add(persist(new Pizza( 1, "Pizza Margherita", 4.5)));
        this.pizzaList.add(persist(new Pizza( 2, "Pizza Thunfisch", 5.0)));
        this.pizzaList.add(persist(new Pizza( 3, "Pizza Salami", 5.0)));
        this.pizzaList.add(persist(new Pizza( 4, "Pizza Schinken", 5.0)));
        this.pizzaList.add(persist(new Pizza( 5, "Pizza Broccoli", 6.0)));
    }
    
    @Produces
    public List<Pizza> getPizzaList() {
        return this.pizzaList;
    }
    
    private Pizza persist(Pizza p) {
        try {
            this.em.persist(p);
        } catch(Exception e) {
            
        }
        return p;
    }
}
