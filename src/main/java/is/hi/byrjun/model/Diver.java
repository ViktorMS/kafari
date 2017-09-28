package is.hi.byrjun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author Einar
 * 
 * Diver klasi inniheldur nafn og lykilorð kafara
 */

// Skilgreinum Entity og Table fyrir gagnagrunninn
@Entity
@Table (name="diver")
public class Diver {
    
    // Skilgrein id sem auðkenni (e. identity)  hlutarins 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    // Smiður til að búa til tóman hlut. Hefur enga parametra
    public Diver() {
    }
    
    public Diver (String n, String p) {
        name = n;
        password = p;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return String.format("<BR>" + "Name: "+name + "<BR>" +"password: "+password);
    }
}
