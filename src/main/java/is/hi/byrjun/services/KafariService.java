package is.hi.byrjun.services;

import is.hi.byrjun.model.Dive;
import is.hi.byrjun.model.Diver;
import java.util.List;

/**
 *
 * @author Einar
 *
 * Þjónusta sem athugar hvort nafn er á réttu formi
 *
 */
public interface KafariService {


    /**
     * Bætir við kennara í kennariRep
     *
     * @param k Kennari
     */
    public void addDiver(Diver d);

    /**
     * Skilar öllum users í userRep
     *
     * @return listi af users
     */
    public List<Diver> allDivers();
    
    public Diver save(Diver diver);
    
    /**
     * Finnur diver með nafn name pg lykilorð password
     * @param name
     * @param password
     * @return 
     */
    public Diver findDiver(String name, String password);
    
    /**
     * Bætir við dive
     *
     */
    public void addDive(Dive dive);
    
     /**
     * Skilar öllum dives fyrir kafara með id id
     *
     */   
    public List<Dive> allDives(long id);
}
