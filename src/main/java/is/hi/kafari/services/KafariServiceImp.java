package is.hi.kafari.services;

import is.hi.kafari.exceptions.DataException;
import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import is.hi.kafari.repository.DiveRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.hi.kafari.repository.DiverRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Einar
 * @date október 2017
 *
 * Þjónusta sem sér um samskipti við repositories
 */
@Service
public class KafariServiceImp implements KafariService {

    // Tenging yfir í safn af köfurum
    @Autowired
    DiverRepository diverRep;
    @Autowired
    DiveRepository diveRep;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // núverandi innsrkáður diver
    private Diver currentDiver = null;

    /**
     * Skilar núverandi innskráðum diver
     *
     * @return currentdiver
     */
    @Override
    public Diver getCurrentDiver() {
        return currentDiver;
    }

    /**
     * Setur viðfangið diver sem currentdiver
     *
     * @param diver nýr diver sem var að skrá sig inn
     */
    @Override
    public void setCurrentDiver(Diver diver) {
        this.currentDiver = diver;
    }

    /**
     * Skilar true ef currentDiver er ekki null, annars false
     *
     * @return true eða false eftir því hvort currentDiver er set
     */
    @Override
    public boolean isCurrentDiverSet() {
        return currentDiver != null;
    }

    /**
     * Bætir við diver í diverRep
     *
     * @param d diver
     * @throws is.hi.kafari.exceptions.DataException
     */
    @Override
    public void addDiver(Diver d) throws DataException {
        String hashedPassword = passwordEncoder.encode(d.getPassword());
        //System.out.println(hashedPassword);
        d.setPassword(hashedPassword);
        try {
            diverRep.save(d);    // Notum save en ekki add
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum divers í diverRep
     *
     * @return listi af divers
     */
    @Override
    public List<Diver> allDivers() throws DataException {
        try {
            return diverRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Vistar kafara
     *
     * @param diver kafari sem skal vista
     * @return kafarinn sem vistaður var
     */
    @Override
    public Diver save(Diver diver) throws DataException {
        try {
            return diverRep.save(diver);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Finnur diver með nafn name og lykilorð password
     *
     * @param name
     * @param password
     * @return diver with matching name and password if exists, else null
     */
    @Override
    public Diver findDiver(String name, String password) throws DataException {
        try {
            List<Diver> divers = diverRep.findByName(name);
            if (divers.isEmpty()) {
                return null;
            }
            for (Diver d : divers) {
                if (passwordEncoder.matches(password, d.getPassword())) {
                    return d;
                }
            }
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
        return null;
    }

    /**
     * Bætir við dive
     *
     * @param dive dýfan sem bæta skal við
     */
    @Override
    public void addDive(Dive dive) throws DataException {
        try {
            Diver d = dive.getDiver();
            d.addDive(dive);
            diveRep.save(dive);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum dives fyrir diver með id id
     *
     * @param id id fyrir diver sem skal finna dives fyrir
     * @return listi af dives sem tilheyra diver með id id
     */
    @Override
    public List<Dive> allDives(long id) throws DataException {
        try {
            return diveRep.findByDiverId(id);    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }
}
