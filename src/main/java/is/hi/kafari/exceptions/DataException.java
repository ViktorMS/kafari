package is.hi.kafari.exceptions;

/**
 *
 * @author Einar
 * @date nóvember 2017
 * HBV501G Hugbúnaðarverkefni 1
 * Háskóli Íslands
 */
public class DataException extends Exception{
    
    public DataException (Exception e) {
        super(" database operation unsuccessful");
    }
}
