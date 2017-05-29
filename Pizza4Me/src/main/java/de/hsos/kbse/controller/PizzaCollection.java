package de.hsos.kbse.controller;

import de.hsos.kbse.entity.Pizza;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class PizzaCollection extends Repository<Pizza>
{
    private List<Pizza> pizzaList;
    
    public PizzaCollection()
    {
        this.pizzaList = new ArrayList();
        this.type = Pizza.class;
    }
    
    @PostConstruct
    public void initialize()
    {
        TypedQuery<Pizza> query = this.em.createNamedQuery("Pizza.FindAll", Pizza.class);
        this.pizzaList = (List)query.getResultList();
        
        for(Pizza p : this.pizzaList)
        {
            p.setQuantity(1);
        }
    }
    
    public void fillDefault()
    {
        this.pizzaList.add(this.persist(new Pizza("Champignon", 5.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Chicken", 6.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Hawaii", 6.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Margherita", 4.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Pepperoni", 6.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Salami", 5.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Spinach", 5.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Tuna", 6.99, 1)));
        this.pizzaList.add(this.persist(new Pizza("Veggie", 5.99, 1)));
    }

    public List<Pizza> getPizzaList() 
    {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) 
    {
        this.pizzaList = pizzaList;
    }
    
    public void clear()
    {
        this.pizzaList.clear();
    }
    
    public int getSize()
    {
        return this.pizzaList.size();
    }
    
    public Pizza findPizzaById(int id)
    {
        TypedQuery<Pizza> query = this.em.createNamedQuery("Pizza.FindById", Pizza.class);
        
        query.setParameter("id", id);
        
        try
        {
            return query.getSingleResult();
        }
        
        catch(Exception e)
        {
            return null;
        }
    }
}
