package de.hsos.kbse.controller;

import de.hsos.kbse.entity.Pizza;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PizzaRepository extends Repository<Pizza>
{
    private List<Pizza> pizzaList;
    
    @Inject
    private Conversation conversation;
    
    public PizzaRepository()
    {
        this.type = Pizza.class;
    }
    
    public void initConversation()
    {
        if(this.conversation.isTransient()) 
        {
            this.conversation.begin();
            this.pizzaList = new ArrayList<>();
        }
    }
    
    public void endConversation()
    {
        if(!this.conversation.isTransient())
        {
            this.conversation.end();
        }
    }
    
    public void add(Pizza pizza)
    {
        Pizza existingPizza = null;
        
        for(Pizza p : this.pizzaList)
        {
            if(p.equals(pizza))
            {
                existingPizza = p;
                break;
            }
        }
        
        if(existingPizza != null)
        {
            existingPizza.setQuantity(existingPizza.getQuantity() + pizza.getQuantity());
        }
        
        else
        {
            this.pizzaList.add(new Pizza(pizza));
        }
    }
    
    public void decrement(Pizza pizza)
    {
        if(pizza.getQuantity() < 2)
        {
            this.pizzaList.remove(pizza);
        }
        
        else
        {
            for(Pizza p : this.pizzaList)
            {
                if(p.equals(pizza))
                {
                    p.setQuantity(p.getQuantity() - 1);
                    break;
                }
            }
        }
    }
    
    public void increment(Pizza pizza)
    {
        for(Pizza p : this.pizzaList)
        {
            if(p.equals(pizza))
            {
                p.setQuantity(p.getQuantity() + 1);
                break;
            }
        }
    }
    
    @Override
    public void remove(Pizza pizza)
    {
        this.pizzaList.remove(pizza);
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        
        for(Pizza p : this.pizzaList)
        {
            totalPrice += p.getPrice() * p.getQuantity();
        }
        
        return totalPrice;
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
        this.conversation.end();
    }
}
