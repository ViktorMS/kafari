package is.hi.kafari.controller;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.DiveForm;
import is.hi.kafari.model.Diver;
import is.hi.kafari.services.KafariService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Einar
 * date: september 2017
 *
 * Tekur við skipunum frá vefviðmóti til að skrá nýjar dýfur, 
 * sýna upplýsingar um allar dýfur og sýna alla notendur
 */
@Controller
@RequestMapping("") // Notice here that the Request Mapping is set at the Class level
public class DiverController {

    private static final ResourceBundle bundle_en_US = ResourceBundle.getBundle("Bundle_en_US");

    // Tenging yfir í þjónustu klasa fyrir forritið 
    @Autowired
    KafariService kafariService;
    
    @Autowired
    LoginController loginController;
    
   /**
     * Birtir síðu með formi til að skrá dýfu
     *
     * @param diver kafari
     * @param model síðumodel
     * @return síða til að skrá dýfu
     */        
    @RequestMapping(value = "/diveForm", method = {RequestMethod.POST, RequestMethod.GET})
    public String diveForm(@ModelAttribute("diver") Diver diver,
            ModelMap model) {
        Diver d = kafariService.getCurrentDiver();
        if (d == null) {
            // sendir notanda aftur á login síðu ef ekki innskráður
            loginController.currentMessage.setMessage(bundle_en_US.getString("ACCESS_RESTRICED"));
            model.addAttribute("message", loginController.currentMessage);
            return "login";
        }
        model.addAttribute("diver", d);
        return "diveForm";
    }
    
    /**
     * Birtir síðu með öllum dýfum
     *
     * @param model síðumodel
     * @return síða með öllum dýfum fyrir núverandi notanda
     */    
    @RequestMapping(value = "/showAllDives", method = RequestMethod.GET)
    public String showAllDives(Model model) {
        Diver d = kafariService.getCurrentDiver();
        if (d == null) {
            // sendir notanda aftur á login síðu ef ekki innskráður
            loginController.currentMessage.setMessage(bundle_en_US.getString("ACCESS_RESTRICED"));
            model.addAttribute("message", loginController.currentMessage);
            return "login";
        }
        ArrayList<Dive> list = new ArrayList(d.getDives());
        model.addAttribute("dives", list);
        return "showAllDives";
    }

    
   /**
     * Sendir notanda aftur í aðalvalmynd
     *
     * @return síða með upplýsingum um notanda
     */       
    @RequestMapping(value = "/logDive", method = RequestMethod.POST)
    public String logDive() {
        return "showDiver";
    }
    
    /**
     * Skráir dýfu í gagnagrunn og birtir yfirlit yfir dýfu
     * 
     * @param diveForm inniheldur parametra fyrir dýfuna
     * @param villur villur ef parametrar eru ólöglegir
     * @param model síðumodel
     * @return síða með nánari upplýsingum um dýfuna eða dýfusíða aftur með
     *         villuskilaboðum ef parametrar eru ólöglegir
     */
    @RequestMapping(value = "/calculateDecompression", method = RequestMethod.POST)
    public String calculateDecompression(@Valid @ModelAttribute("diveForm") DiveForm diveForm, 
            BindingResult villur,
            ModelMap model) {
        if (villur.hasErrors()) {
            loginController.currentMessage.setMessage(bundle_en_US.getString("ERROR")+villur.getFieldError().getDefaultMessage() +bundle_en_US.getString("DIV"));
            model.addAttribute("message", loginController.currentMessage);
            return "diveForm";
        }
         // log dive to database!
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String location = diveForm.getDivingLocation();
        int timeInt = diveForm.getTotalTime();
        int depthInt = diveForm.getMaxDepth();
        String letter = TableLookupController.getLetter(depthInt, timeInt);
        String otu = TableLookupController.getOTU(depthInt, timeInt);
        int otuInt = Integer.parseInt(otu);
        
        //ATH: Virkar ekki
        if (otuInt>TableLookupController.getOTUReducedLungCapacity() ){
            loginController.currentMessage.setMessage(bundle_en_US.getString("DANGEROUS_DIVE_WARNING"));
        }else if(otuInt>TableLookupController.getOTUDailyMax()){
            loginController.currentMessage.setMessage(bundle_en_US.getString("OTU_WARNING"));
        }else if(otuInt>TableLookupController.getOTUDailyLimit()){
            loginController.currentMessage.setMessage(bundle_en_US.getString("OTU_RECOMMENDATION"));
        }
        
        
        String decompression = TableLookupController.getDecompressionString(depthInt, timeInt);
        if(decompression==null){
            loginController.currentMessage.setMessage(bundle_en_US.getString("ILLEGAL_DIVE"));
            model.addAttribute("message", loginController.currentMessage);
            return "diveForm";
        }
        
        Diver d = kafariService.getCurrentDiver();
        Dive dive = new Dive(d, ts, location, timeInt, depthInt, decompression, letter, otuInt);
        
        kafariService.addDive(dive);
        model.addAttribute("letter", letter);
        model.addAttribute("otu", otu);
        model.addAttribute("decompression", decompression);
        model.addAttribute("depth", depthInt);
        model.addAttribute("time", timeInt);
        model.addAttribute("location", location);
        return "showDive";
    }
    
    /**
     * Birtir síðu með öllum köfurum
     *
     * @param model síðumodel
     * @return síða með öllum köfurum í kerfinu
     */    
    @RequestMapping(value = "/allDivers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        ArrayList<Diver> list;
        list = (ArrayList<Diver>) kafariService.allDivers();
        model.addAttribute("divers", list);
        return "allDivers";
    }
    

}
