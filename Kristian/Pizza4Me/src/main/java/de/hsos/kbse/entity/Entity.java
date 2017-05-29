package de.hsos.kbse.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Access(AccessType.FIELD)
@Dependent
public abstract class Entity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    
    @Version
    private long version;
    
    public int getId()
    {
        if(this.id != null)
        {
            return id;
        }
        
        return -1;
    }
    
    @Override
    public boolean equals(Object object) 
    {
        if(object == null || id == null)
        {
            return false;
        }
        
        if(this.getClass() != object.getClass())
        {
            return false;
        }
        
        Entity entity = (Entity)object;
        
        return Objects.equals(this.id, entity.id);
    }
  
    @Override
    public int hashCode() 
    {
        return 31;
    }
}
