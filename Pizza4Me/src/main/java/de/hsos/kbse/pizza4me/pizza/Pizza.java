package de.hsos.kbse.pizza4me.pizza;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Access(AccessType.FIELD)
@Vetoed
@NamedQuery(name ="findAllPizzas", query="SELECT p FROM Pizza p")
public class Pizza implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer Id;
    
    private String name;
    private double price;    
    
    public Pizza (int Id, String name, double price) {
        this.Id = Id;
        this.name = name;
        this.price = price;
    }

    public Pizza() {
    }

    public int getNr() {
        return Id;
    }

    public void setNr(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
