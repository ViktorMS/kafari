package is.hi.byrjun.controller;

import is.hi.byrjun.model.Dive;
import is.hi.byrjun.model.Diver;
import is.hi.byrjun.services.KafariService;
import java.sql.Timestamp;
import java.util.ArrayList;
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
 * Tekur við skipunum frá vefviðmóti til að skrá kafara og sýna upplýsingar
 */
@Controller
@RequestMapping("") // Notice here that the Request Mapping is set at the Class level
public class LoginController {

    // Tenging yfir í þjónustu klasa fyrir forritið 
    @Autowired
    KafariService kafariService;
    
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
    @RequestMapping(value = "/showDiver", method = RequestMethod.POST)
    public String showDiver(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            ModelMap model) {
        Diver d = kafariService.findDiver(name, password);
        if (d == null) {
            // skilar villusíðu ef kafari ekki í kerfinu
            return "invalidDiver";
        }
        currentDiver = d;
        model.addAttribute("diver", d);
        return "showDiver";
    }
    
   /**
     * Birtir síðu með formi til að skrá dýfu
     *
     * @param diver kafari
     * @param model síðumodel
     * @return síða til að skrá dýfu
     */        
    @RequestMapping(value = "/diveForm", method = RequestMethod.POST)
    public String diveForm(@ModelAttribute("diver") Diver diver,
            ModelMap model) {
        model.addAttribute("diver", currentDiver);
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
        ArrayList<Dive> list;
        list = (ArrayList<Dive>) kafariService.allDives(currentDiver.getId());
        model.addAttribute("dives", list);
        return "showAllDives";
    }

    
   /**
     * Skráir dýfu í gagnagrunn og birtir yfirlit yfir notanda
     *
     * @param location staður
     * @param time lengd dýfu
     * @param depth hámarksdýpt dýfu
     * @param decompression 
     * @param letter stafur
     * @param model síðumodel
     * @return síða með upplýsingum um notanda
     */       
    @RequestMapping(value = "/logDive", method = RequestMethod.POST)
    public String logDive(@RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "time", required = false) String time,
            @RequestParam(value = "depth", required = false) String depth,
            @RequestParam(value = "decompression", required = false) String decompression,
            @RequestParam(value = "letter", required = false) String letter,
            ModelMap model) {
        model.addAttribute("diver", currentDiver);
        // log dive to database!
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Dive dive = new Dive(currentDiver.getId(), ts, location, Integer.parseInt(time), Integer.parseInt(depth), Integer.parseInt(decompression), letter);
        //System.out.println(dive);
        kafariService.addDive(dive);
        return "showDiver";
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
