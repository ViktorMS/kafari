package is.hi.byrjun.repository;

import is.hi.byrjun.model.Dive;
import is.hi.byrjun.model.Diver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Einar
 */

public interface DiveRepository extends JpaRepository<Dive, Long>{
    /**
     * Nær í allar dýfur
     * @return listi af dýfum
     */
    @Override
    List<Dive> findAll();
    
    /**
     * Bætir við dive 
     * @param dive
     * @return 
     */
    @Override
    Dive save (Dive dive);

   @Override
   Dive findOne(Long id);
   
   // Notið sama nafn og dálkanafn í töflunni/tilviksbreytan (e. attribute) 
   List<Dive> findByDiverId(long diverId);
}
