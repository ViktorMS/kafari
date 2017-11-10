package is.hi.kafari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * @author Team 4
 * @date september 2017
 *
 * Main fall sem keyrir upp Spring forritið.
  */

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    /**
     *
     * @param applicationBuilder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
