package is.hi.kafari.controller;

import is.hi.byrjun.exceptions.DiverNotFound;
import is.hi.kafari.exceptions.DataException;
import is.hi.kafari.model.Diver;
import is.hi.kafari.model.Message;
import is.hi.kafari.services.KafariService;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Einar
 * @date september 2017
 *
 * Tekur við skipunum frá vefviðmóti til að skrá kafara inn og út og bæta við
 * nýjum köfurum
 */
@Controller
@RequestMapping("") // Notice here that the Request Mapping is set at the Class level
public class LoginController {

    // geymir "external" strengi
    private static final ResourceBundle bundle_en_US = ResourceBundle.getBundle("Bundle_en_US");

    // Tenging yfir í þjónustu klasa fyrir forritið 
    @Autowired
    KafariService kafariService;

    // Tenging yfir í dýfutjórnanda
    @Autowired
    DiverController diverController;

    // Skilaboð sem eru sett sem model attribute til að
    // koma skilaboðum til notanda
    Message currentMessage = new Message();

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Birtir login síðu
     *
     * @param model síðumodel
     * @return login síða
     */
    @RequestMapping("")
    public String logIn(ModelMap model) {
        // check if user is logged in, then show dashboard
        Diver d = kafariService.getCurrentDiver();
        currentMessage.setMessage("");
        // check if user is logged in, then show dashboard
        if (d != null) {
            model.addAttribute("diver", d);
            return "showDiver";
        }
        // else, show the login menu
        currentMessage.setMessage(bundle_en_US.getString("LOGIN_ERROR"));
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
     * @param villur ef einhverjar villur hafa orðið í forminu
     * @param model síðumodel
     * @return síða með staðfestingu um að kafara hafi verið bætt við gagnagrunn
     * eða addDiver síðu aftur með villuskilaboðum ef parametrar voru ólöglegir
     * @throws is.hi.kafari.exceptions.DataException
     */
    @RequestMapping(value = "/diverAdded", method = RequestMethod.POST)
    public String diverAdded(@Valid @ModelAttribute("diver") Diver diver,
            BindingResult villur,
            ModelMap model) throws DataException {
        if (villur.hasErrors()) {
            currentMessage.setMessage(bundle_en_US.getString("ALERT") + villur.getFieldError().getDefaultMessage() + bundle_en_US.getString("DIV"));
            model.addAttribute("message", currentMessage);
            return "addDiver";
        }
        model.addAttribute("diver", diver);
        kafariService.addDiver(diver);
        kafariService.setCurrentDiver(diver);
        String diverAddedMessage = bundle_en_US.getString("ALERT") + diver.getName() + bundle_en_US.getString("USER_ADDED");

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
    public String showDiver(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            ModelMap model
    ) {

        if (!kafariService.isCurrentDiverSet()) {
            // skilar login síðu ef kafari ekki í kerfinu
            currentMessage.setMessage(bundle_en_US.getString("LOGIN_ERROR"));
            model.addAttribute("message", currentMessage);

            return "login";
        }
        Diver d = kafariService.getCurrentDiver();
        currentMessage.setMessage("");
        model.addAttribute("message", currentMessage);

        model.addAttribute("diver", d);
        return "showDiver";
    }

    /**
     * Ef notendanafn er í kerfinu og lykilorð passar við það þá fær notandi
     * yfirlitssíðu, annars fer hann aftur á login síðu með villuskilaboðum.
     *
     * @param name notendanafn
     * @param password lykilorð
     * @param model síðumódel
     * @return yfirlitssíðu fyrir kafara ef hann er gildur, annars login síðu
     * @throws is.hi.byrjun.exceptions.DiverNotFound
     * @throws is.hi.kafari.exceptions.DataException
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public String showDiverLogin(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            ModelMap model
    ) throws DiverNotFound, DataException {
        Diver d = kafariService.getCurrentDiver();
        if (d == null) {

            d = kafariService.findDiver(name, password);
            if (d == null) {
                // skilar login síðu ef kafari ekki í kerfinu
                //currentMessage.setMessage(bundle_en_US.getString("INCORRECT_LOGIN"));
                //model.addAttribute("message", currentMessage);
                //return "login";
                //Exception KennariFannstEkki búin til
                
                // exception:
                throw (new DiverNotFound(name));
            }
            kafariService.setCurrentDiver(d);
        }
        currentMessage.setMessage("");
        model.addAttribute("message", currentMessage);

        model.addAttribute("diver", d);
        return "showDiver";
    }

    /**
     * Meðhöndlum DiverNotFound exception
     *
     * @param request Http beiðnin
     * @param e undantekningin (e. exception)
     * @param model fyrir notendaviðmótið
     * @return síða sem sýnir villuboð
     */
    @ExceptionHandler(DiverNotFound.class)
    public String diverNotFound(HttpServletRequest request,
            DiverNotFound e, Model model) {

        LOGGER.error("URL " + request.getRequestURL());
        LOGGER.error("Exception " + e);
        // Hér má líka ná í aðrar upplýsingar um e eins og 
        // e.getCause() e.getMessage() sjá nánar hér 
        // https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html
        model.addAttribute("nafn", e.getName());
        return "diverNotFound";
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
        currentMessage.setMessage(bundle_en_US.getString("LOGGED_OUT"));
        model.addAttribute("message", currentMessage);
        return "login";
    }

    ////////////////////////////////////
    // Föll til að prófa villumeðhöndlun
    ////////////////////////////////////
    // Framkallar gagnagrunnsvillu - sett hér til að hægt sé að prófa hvað gerist þegar
    // villan verður 
    @RequestMapping("/dbVilla")
    String framkallarSQLException() throws SQLException {
        throw new SQLException();
    }

    // Framkallar gagnagrunnsvillu - sett hér til að hægt sé að prófa hvað gerist þegar
    // villan verður 
    @RequestMapping("/almennVilla")
    String throwAlmenUndantekning() throws Exception {
        throw new Exception("almenn undantekning");
    }
}
