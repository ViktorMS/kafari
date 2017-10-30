package is.hi.kafari.services;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import java.util.List;

/**
 *
 * @author Einar
 * @date október 2017
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
    
    /**
     * Vistar kafara
     * @param diver kafari sem skal vista
     * @return kafarinn sem vistaður var
     */
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
     * @param dive dýfan sem bæta skal við
     */
    public void addDive(Dive dive);
    
    /**
    * Skilar öllum dives fyrir diver með id id
    *
    * @param id id fyrir diver sem skal finna dives fyrir
    * @return listi af dives sem tilheyra diver með id id
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
    
    /**
     * Skilar true ef currentDiver er ekki null, annars false
     * @return true eða false eftir því hvort currentDiver er set
     */
    public boolean isCurrentDiverSet();
}
