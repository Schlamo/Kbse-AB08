package de.hsos.kbse.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class Login extends de.hsos.kbse.entity.Entity
{
    private String email;
    private String password;
    private boolean locked;
    private int numLogins;
    private java.sql.Timestamp timestamp;
    
    public Login()
    {
        
    }
    
    public Login(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }
    
    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public boolean isLocked() 
    {
        return locked;
    }

    public void setLocked(boolean locked) 
    {
        this.locked = locked;
    }

    public java.sql.Timestamp getTimestamp() 
    {
        return timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) 
    {
        this.timestamp = timestamp;
    }

    public int getNumLogins() 
    {
        return numLogins;
    }

    public void setNumLogins(int numLogins) 
    {
        this.numLogins = numLogins;
    }
}
