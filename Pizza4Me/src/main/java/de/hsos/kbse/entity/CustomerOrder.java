package de.hsos.kbse.entity;

import java.util.List;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class CustomerOrder extends de.hsos.kbse.entity.Entity {
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CUSTOMERORDERITEM_ID")
    private List<CustomerOrderItem> items; 
    
    public CustomerOrder()
    {
        
    }
    
    public CustomerOrder(List items)
    {
        this.items = items;
    }

    public List<CustomerOrderItem> getItems() 
    {
        return items;
    }

    public void setItems(List<CustomerOrderItem> items) 
    {
        this.items = items;
    }
}
