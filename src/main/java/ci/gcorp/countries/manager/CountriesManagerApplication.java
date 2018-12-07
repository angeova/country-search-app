package ci.gcorp.countries.manager;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CountriesManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CountriesManagerApplication.class, args);
    }
}
