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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
/**
 *
 * @author Viktor
 * @date október 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 * 
 * Prófunarklasi sem framkvæmir prófanir á weblayer og notar WebMvcTest
*/
@RunWith(SpringRunner.class)
// Sleppum hér @SpringBootTest
/**
 *  Aðeins veflagið er keyrt upp en ekki allur "context"-inn
 *  Getum beðið um að keyra bara upp KennariController klasann 
 */
@WebMvcTest (LoginController.class)       
                                            
public class WebLayerTest {
    
        // Þjónninn ekki keyrður upp 
        @Autowired
        private MockMvc mockMvc;
        
        @MockBean
        KafariService kafariService;
        
        @MockBean
        DiverController diverController;
        
        /**
         * Aðferð til að athuga hvort virkar að senda HttpRequest á showAllDives
         * og fá til baka showAllDives.html síðuna 
         */
	@Test 
        public void showAllDivesSkilarShowAllDives() throws Exception {
        this.mockMvc.perform(get("/showAllDives"))                
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("showAllDives"));
    }

}
