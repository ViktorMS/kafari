package is.hi.kafari.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @OneToMany(mappedBy="diver", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Dive> dives= new HashSet<Dive>();

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

    public Set<Dive> getDives() {
        return dives;
    }

    public void setDives(Set<Dive> dives) {
        this.dives = dives;
    }
    
    @Override
    public String toString() {
        return String.format("<BR>" + "Name: "+name + "<BR>" +"password: "+password);
    }
}
