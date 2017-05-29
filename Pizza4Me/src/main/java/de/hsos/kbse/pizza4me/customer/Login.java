package de.hsos.kbse.pizza4me.customer;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.inject.Named;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Vetoed
public class Login implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    
    private String username;
    private String email;
    private String password;
    
    public Login() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
