package is.hi.kafari.repository;

import is.hi.kafari.model.Diver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Einar
 */

public interface DiverRepository extends JpaRepository<Diver, Long>{
    /**
     * Nær í alla kennara
     * @return listi af kennurum 
     */
    @Override
    List<Diver> findAll();
    
    /**
     * Bætir við kennara
     * @param diver 
     * @return  
     */
    @Override
    Diver save (Diver diver);

   @Override
   Diver findOne(Long id);
   
   // Notið sama nafn og dálkanafn í töflunni/tilviksbreytan (e. attribute) 
   List<Diver> findByNameAndPassword(String name, String password);
}
