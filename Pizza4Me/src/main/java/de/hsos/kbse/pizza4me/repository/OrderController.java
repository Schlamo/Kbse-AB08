package de.hsos.kbse.pizza4me.repository;

import de.hsos.kbse.pizza4me.pizza.Oorder;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
@Named
public class OrderController {
    @PersistenceContext(unitName="CustomerPU")
    private EntityManager em;
    
    public void persist(Oorder order) {
        em.persist(order);
    }
    
}
