package is.hi.kafari;


import is.hi.kafari.controller.DiverController;
import is.hi.kafari.controller.LoginController;
import is.hi.kafari.services.KafariService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

// Athugið vel að þessi import séu rétt 
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 *
 * @author Viktor, Einar
 * @date október 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 * 
 * Prófunarklasi sem framkvæmir prófanir á weblayer og notar WebMvcTest og
 * Mockito til að prófa service klasa 
*/

@RunWith(SpringRunner.class)
/**
 *  Aðeins veflagið er keyrt upp en ekki allur "context"-inn
 *  Getum beðið um að keyra bara upp LoginController klasann 
 *  Biðjum um að bæta KafariService inn í "context-inn" sem Mock (prófanahlut)
 */
@WebMvcTest(LoginController.class)     
                                            
public class WebMockTest {

    // Þjónninn (Tomcat) ekki keyrður upp 
    @Autowired
    private MockMvc mockMvc;

    // Bætum við prófunar (e. Mock) service klasa - kemur úr springframework safninu (fyrir Mockito
    // sérstaklega gert fyrir Mockito safnið 
    @MockBean
    KafariService kafariService;
    
    @MockBean
    DiverController diverController;
    
    /**
     * Aðferð sem prófar /showDiver á LoginController en með
     * isCurrentDiverSet() true. Ættum að fá til baka showDiver.html síðuna
     * @throws java.lang.Exception
     */
    @Test
    public void isCurrentDiverSetTrue() throws Exception {
        // Látum isCurrentDiverSet() skila null 
        // Notum Mockito í prófanirnar - Mockito er Framework fyrir unit testing í Java 
        // http://site.mockito.org/   
        
        // Prófið ætti að takast - prófum ósönnu leiðina í if-setningunni
        when(kafariService.isCurrentDiverSet()).thenReturn(true);
        this.mockMvc.perform(get("/showDiver"))
                .andDo(print())
                .andExpect(status()
                .isOk())
                .andExpect(view().name("showDiver"));
      
    }

    /**
     * Aðferð sem prófar /showDiver á LoginController en með
     * isCurrentDiverSet() false. Ættum að fá til baka login.html síðuna
     * @throws java.lang.Exception
     */
    @Test
    public void isCurrentDiverSetFalse() throws Exception {
        // Látum isCurrentDiverSet() skila null 
        // Notum Mockito í prófanirnar - Mockito er Framework fyrir unit testing í Java 
        // http://site.mockito.org/   
        
        // Prófið ætti að takast - prófum sönnu leiðina í if-setningunni
        when(kafariService.isCurrentDiverSet()).thenReturn(false);
        this.mockMvc.perform(get("/showDiver"))
                .andDo(print())
                .andExpect(status()
                .isOk())
                .andExpect(view().name("login"));
      
    }
    
    /**
     * Prófið ætti að mistakast - prófum ósönnu leiðina isCurrentDiverSet() en berum
     * saman við rangan streng 
     * @throws Exception 
     */
    @Test
    public void isCurrentDiverSetFalseWithWrongComparison() throws Exception {
        // Látum isCurrentDiverSet() skila null 
        // Notum Mockito í prófanirnar - Mockito er Framework fyrir unit testing í Java 
        // http://site.mockito.org/   
        
        // Prófið ætti að ekki takast - prófum sönnu leiðina í if-setningunni 
        when(kafariService.isCurrentDiverSet()).thenReturn(false);
        this.mockMvc.perform(get("/showDiver"))
                .andDo(print())
                .andExpect(status()
                .isOk())
                .andExpect(view().name("asdf"));
    }

}
