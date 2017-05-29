
package de.hsos.kbse.controller;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
public abstract class Repository<T extends de.hsos.kbse.entity.Entity> implements Serializable
{
    @PersistenceContext(unitName = "Pizza4MePU")
    protected EntityManager em;
    protected Class<?> type;
    
    public T persist(T entity)
    {
        try
        {
            this.em.persist(entity);
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return entity;
    }
    
    public T find(int id)
    {
        return (T)this.em.find(this.type, id);
    }
    
    public void remove(T entity)
    {
        this.em.remove(entity);
    }
    
    public T merge(T entity)
    {
        return this.em.merge(entity);
    }
}
