package is.hi.kafari.services;

import is.hi.kafari.exceptions.DataException;
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
     * @throws is.hi.kafari.exceptions.DataException
     */
    public void addDiver(Diver d) throws DataException;

    /**
     * Skilar öllum divers í diverRep
     *
     * @return listi af divers
     * @throws is.hi.kafari.exceptions.DataException
     */
    public List<Diver> allDivers() throws DataException;
    
    /**
     * Vistar kafara
     * @param diver kafari sem skal vista
     * @return kafarinn sem vistaður var
     * @throws is.hi.kafari.exceptions.DataException
     */
    public Diver save(Diver diver) throws DataException;
    
    /**
     * Finnur diver með nafn name og lykilorð password
     * @param name
     * @param password
     * @return 
     * @throws is.hi.kafari.exceptions.DataException 
     */
    public Diver findDiver(String name, String password) throws DataException;
    
    /**
     * Bætir við dive
     *
     * @param dive dýfan sem bæta skal við
     * @throws is.hi.kafari.exceptions.DataException
     */
    public void addDive(Dive dive) throws DataException;
    
    /**
    * Skilar öllum dives fyrir diver með id id
    *
    * @param id id fyrir diver sem skal finna dives fyrir
    * @return listi af dives sem tilheyra diver með id id
     * @throws is.hi.kafari.exceptions.DataException
    */   
    public List<Dive> allDives(long id) throws DataException;

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
