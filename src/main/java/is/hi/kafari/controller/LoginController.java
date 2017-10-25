package is.hi.kafari.controller;

import is.hi.kafari.model.Diver;
import is.hi.kafari.model.Message;
import is.hi.kafari.services.KafariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Einar
 * date: september 2017
 *
 * Tekur við skipunum frá vefviðmóti til að skrá kafa inn og út
 * og bæta við nýjum köfurum
 */
@Controller
@RequestMapping("") // Notice here that the Request Mapping is set at the Class level
public class LoginController {

    // Tenging yfir í þjónustu klasa fyrir forritið 
    @Autowired
    KafariService kafariService;
    
    @Autowired
    DiverController diverController;
    
    Message currentMessage = new Message();
    
    
    /**
     * Birtir login síðu
     *
     * @return login síða
     */
    @RequestMapping("")
    public String logIn(ModelMap model) {
        // check if user is logged in, then show dashboard
        Diver d = kafariService.getCurrentDiver();
        currentMessage.setMessage("");
        // check if user is logged in, then show dashboard
        if(d != null) 
        {
            model.addAttribute("diver", d);
            return "showDiver";
        }
        // else, show the login menu
        currentMessage.setMessage("<div class=\"alert alert-info\" role=\"alert\"> <strong>Hello there!</strong> Please log in with your username and password. </div>");
        model.addAttribute("message", currentMessage);
        return "login";
    }
    
    /**
     * Birtir síðu til að bæta við kafara/notanda
     *
     * @return addDriver síða
     */    
    @RequestMapping("/addDiver")
    public String addDiver() {
        return "addDiver";
    }

    /**
     * Bætir kafara við gagnagrunn
     *
     * @param diver kafari
     * @param model síðumodel
     * @return síða með staðfestingu um að kafara hafi verið bætt við gagnagrunn
     */    
    @RequestMapping(value = "/diverAdded", method = RequestMethod.POST)
    public String diverAdded(@ModelAttribute("diver") Diver diver,
            ModelMap model) {
        model.addAttribute("diver", diver);
        kafariService.addDiver(diver);
        kafariService.setCurrentDiver(diver);
        String diverAddedMessage = "<div class=\"alert alert-success\" role=\"alert\"> "+diver.getName()+" has been added to database </div>";
        
        currentMessage.setMessage(diverAddedMessage);
        model.addAttribute("message", currentMessage);
        
        return "showDiver";
    }

    /**
     * Tekur við nafni og lykilorði frá notanda og birtir upplýsingar um hann
     *
     * @param name nafn 
     * @param password lykilorð
     * @param model síðumodel
     * @return síða sem birtir upplýsingar um notanda
     */    
    @RequestMapping(value = "/showDiver", method = {RequestMethod.POST, RequestMethod.GET})
    public String showDiver
        (
            @RequestParam(value = "name", required = false) 
            String name,
            @RequestParam(value = "password", required = false) 
            String password,
            ModelMap model
        )    
    {
        Diver d = kafariService.getCurrentDiver();
        if (d == null) {
                // skilar login síðu ef kafari ekki í kerfinu
                currentMessage.setMessage("<div class=\"alert alert-info\" role=\"alert\"> <strong>Hello there!</strong> Please log in with your username and password. </div>");
                model.addAttribute("message", currentMessage);
            
                return "login";
        }
        currentMessage.setMessage("");
        model.addAttribute("message", currentMessage);
        
        model.addAttribute("diver", d);
        return "showDiver";
    }
        
        
        
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public String showDiverLogin
        (
            @RequestParam(value = "name", required = false) 
            String name,
            @RequestParam(value = "password", required = false) 
            String password,
            ModelMap model
        )    
    {
        Diver d = kafariService.getCurrentDiver();
        if (d == null) {
            
            d = kafariService.findDiver(name, password);
            if (d == null) {
                // skilar login síðu ef kafari ekki í kerfinu
                currentMessage.setMessage("<div class=\"alert alert-warning\" role=\"alert\"> <strong>Whoops!</strong> Username or password incorrect. </div>");
                model.addAttribute("message", currentMessage);
            
                return "login";
            }
            kafariService.setCurrentDiver(d);
        }
        currentMessage.setMessage("");
        model.addAttribute("message", currentMessage);
        
        model.addAttribute("diver", d);
        return "showDiver";
    }    
    /**
     * Skráir út notanda og sendir aftur á login síðu
     *
     * @param model síðumodel
     * @return login síða
     */    
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(Model model) {
        kafariService.setCurrentDiver(null);
        currentMessage.setMessage("<div class=\"alert alert-info\" role=\"alert\"> <strong>All done!</strong> You have been logged out. </div>");
        model.addAttribute("message", currentMessage);
        return "login";
    }
}
