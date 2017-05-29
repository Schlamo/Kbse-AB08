package de.hsos.kbse.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.FIELD)
@Vetoed
@NamedQueries
(
    {
        @NamedQuery(name = "Pizza.FindAll", query = "select p from Pizza p"),
        @NamedQuery(name = "Pizza.FindById", query = "select p from Pizza p where p.id=:id")
    }
)
public class Pizza extends de.hsos.kbse.entity.Entity
{ 
    private String name;
    private double price;
    
    @Transient
    @Min(value = 1, message = "Mindestbestellmenge beträgt 1!")
    private Integer quantity;
    
    public Pizza()
    {
        
    }
    
    public Pizza(String name, double price, Integer quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Pizza(Pizza pizza)
    {
        this.id = pizza.id;
        this.name = pizza.name;
        this.price = pizza.price;
        this.quantity = pizza.quantity;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice() 
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Integer getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}
