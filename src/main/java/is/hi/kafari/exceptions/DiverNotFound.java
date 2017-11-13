package is.hi.byrjun.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Er Exception sem lýsir því að kennari fannst ekki í gagnagrunni 
 * 
 * @author Ebba Þóra Hvannberg 
 * @date 
 * HBV501G Hugbúnaðarverkefni 1
 * Háskóli Íslands
 */

// Þegar DiverNotFound hlutur (exception) er búinn til er svarið HttpStatus.NOT_FOUND
// sent til forritsins. Ef controller meðhöndlar ekki undantekninguna með @ExceptionHandler
// eða Global Exception handler meðhöndlar hana ekki 
// þá verður til venjuleg Http villa og /error síðan meðhöndlar hana 

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Enginn kennari")  // 404
public class DiverNotFound extends Exception {

    private String nafn;
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(DiverNotFound.class);
    
    public String getNafn() {
        return nafn;
    }
    
    public DiverNotFound(String nafn) {
        super(nafn + " fannst ekki");
        LOGGER.error("Kennari fannst ekki "+nafn);
        this.nafn = nafn;
        
    }
}
