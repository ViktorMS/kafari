package is.hi.byrjun.repository;

import is.hi.byrjun.model.Diver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Einar
 */

public interface DiverRepository extends JpaRepository<Diver, Long>{
    /**
     * Nær í alla kennara
     * @return listi af kennurum 
     */
    List<Diver> findAll();
    
    /**
     * Bætir við kennara
     * @param diver 
     */
    Diver save (Diver diver);

   @Override
   Diver findOne(Long id);
   
   // Notið sama nafn og dálkanafn í töflunni/tilviksbreytan (e. attribute) 
   List<Diver> findByNameAndPassword(String name, String password);
}
