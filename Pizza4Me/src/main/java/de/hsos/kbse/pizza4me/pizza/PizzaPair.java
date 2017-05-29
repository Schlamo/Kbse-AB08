package de.hsos.kbse.pizza4me.pizza;

import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class PizzaPair implements Serializable {    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PIZZA_ID")
    
    private Pizza pizza;
    private int amount;

    public PizzaPair() {
        
    }
    
    public double getPrice() {
        return pizza.getPrice();
    }

    public void setPrice(double price) {
        this.pizza.setPrice(price); 
    }

    public PizzaPair(Pizza p, int amount) {
        this.pizza = p;
        this.amount = amount;
    }

    public String getName() {
        return pizza.getName();
    }

    public void setName(String name) {
        this.pizza.setName(name);
    }
    
    public int getPizzaNr() {
        return pizza.getNr();
    }

    public void setPizzaNr(int pizzaNr) {
        this.pizza.setNr(pizzaNr);
    }

    public int getAmount() {
        return amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}