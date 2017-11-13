package is.hi.byrjun.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Er Exception sem lýsir því að kennari fannst ekki í gagnagrunni 
 * 
 * @author Einar
 * @date nóvember 2017
 * HBV501G Hugbúnaðarverkefni 1
 * Háskóli Íslands
 */

// Þegar DiverNotFound hlutur (exception) er búinn til er svarið HttpStatus.NOT_FOUND
// sent til forritsins. Ef controller meðhöndlar ekki undantekninguna með @ExceptionHandler
// eða Global Exception handler meðhöndlar hana ekki 
// þá verður til venjuleg Http villa og /error síðan meðhöndlar hana 

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No diver found")  // 404
public class DiverNotFound extends Exception {

    private String name;
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(DiverNotFound.class);
    
    public String getName() {
        return name;
    }
    
    public DiverNotFound(String name) {
        super(name + " not found");
        LOGGER.error("Diver not found "+name);
        this.name = name;
        
    }
}
