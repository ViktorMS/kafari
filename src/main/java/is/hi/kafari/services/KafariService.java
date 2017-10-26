package is.hi.kafari.services;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import java.util.List;

/**
 *
 * @author Einar
 *
 * Þjónusta sem sér um samskipti við repositories
 *
 */
public interface KafariService {


    /**
     * Bætir við diver í diverRep
     *
     * @param d diver
     */
    public void addDiver(Diver d);

    /**
     * Skilar öllum divers í diverRep
     *
     * @return listi af divers
     */
    public List<Diver> allDivers();
    
    public Diver save(Diver diver);
    
    /**
     * Finnur diver með nafn name og lykilorð password
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
     * Skilar öllum dives fyrir diver með id id
     *
     */   
    public List<Dive> allDives(long id);

    /**
     * Skilar núverandi innskráðum diver
     * @return currentdiver
     */
    public Diver getCurrentDiver();
    
    /**
     * Setur viðfangið diver sem currentdiver
     * @param diver nýr diver sem var að skrá sig inn
     */
    public void setCurrentDiver(Diver diver);
}
