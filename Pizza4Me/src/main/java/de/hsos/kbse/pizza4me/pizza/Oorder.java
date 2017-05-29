package de.hsos.kbse.pizza4me.pizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class Oorder implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CUSTOMERORDERITEM_ID")
    private List<PizzaPair> pairs;
    
    public Oorder() {
        
    }    
    
    public Oorder(List<PizzaPair> pairs) {
        this.pairs = pairs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void addPair(PizzaPair pair) {
        this.pairs.add(pair);
    };

    public List<PizzaPair> getPairs() {
        return pairs;
    }

    public void setPairs(List<PizzaPair> pairs) {
        this.pairs = pairs;
    }
}
