package is.hi.kafari.services;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import is.hi.kafari.repository.DiveRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.hi.kafari.repository.DiverRepository;

/**
 *
 * @author Einar
 */
@Service
public class KafariServiceImp implements KafariService{

    // Tenging yfir í safn af köfurum
    @Autowired
    DiverRepository diverRep;
    @Autowired
    DiveRepository diveRep;
    
    // núverandi innsrkáður diver
    private Diver currentDiver = null;

    @Override
    public Diver getCurrentDiver() {
        return currentDiver;
    }

    @Override
    public void setCurrentDiver(Diver diver) {
        this.currentDiver = diver;
    }
 
    @Override
    public void addDiver(Diver d) {
         diverRep.save(d);    // Notum save en ekki add
    }

    @Override
    public List<Diver> allDivers() {
        return diverRep.findAll();    // Notum findAll í staðinn fyrir getAll
    }
    
    @Override
    public Diver save(Diver diver) {
        return diverRep.save(diver);
    }
    
    @Override
    public Diver findDiver(String name, String password) {
        List<Diver> divers = diverRep.findByNameAndPassword(name, password);
        if (divers.isEmpty()) {
            return null;
        }
        return divers.get(0);
    }
    
    @Override
    public void addDive(Dive dive) {
        Diver d = dive.getDiver();
        d.addDive(dive);
        diveRep.save(dive);
    }
    
    @Override
    public List<Dive> allDives(long id) {
        return diveRep.findByDiverId(id);    // Notum findAll í staðinn fyrir getAll
    }
}
