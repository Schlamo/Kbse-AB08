package de.hsos.kbse.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class CustomerOrderItem extends de.hsos.kbse.entity.Entity
{
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PIZZA_ID")
    private Pizza pizza;
    
    private int quantity;
    
    public CustomerOrderItem()
    {
        
    }
    
    public CustomerOrderItem(Pizza pizza, int quantity)
    {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Pizza getPizza() 
    {
        return pizza;
    }

    public void setPizza(Pizza pizza) 
    {
        this.pizza = pizza;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
}
