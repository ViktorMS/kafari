package is.hi.kafari;

import is.hi.kafari.controller.LoginController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author viktor jon helgason
 * @date október 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 * 
 * Prófunarklasi sem athugar hvort LoginController keyrir 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    
        @Autowired 
        LoginController loginController;

        /**
         * Aðferð til að athuga hvort LoginController hlutur hafi verið búinn til
         */
        
	@Test
	public void contextLoads() {
        
                   assertThat(loginController).isNotNull();
}

}

