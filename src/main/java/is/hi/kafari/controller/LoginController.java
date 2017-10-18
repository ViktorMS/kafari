package is.hi.kafari.controller;

import is.hi.kafari.model.Diver;
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
    
    // geymir innskráðan diver
    Diver currentDiver = null;
    
    /**
     * Birtir login síðu
     *
     * @return login síða
     */
    @RequestMapping("")
    public String logIn() {
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
        currentDiver = diver;
        return "diverAdded";
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
    public String showDiver(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            ModelMap model) {
        if (currentDiver == null) {
            Diver d = kafariService.findDiver(name, password);
            if (d == null) {
                // skilar villusíðu ef kafari ekki í kerfinu
                return "invalidDiver";
            }
            currentDiver = d;
        }
        
        model.addAttribute("diver", currentDiver);
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
        currentDiver = null;
        return "login";
    }
}
