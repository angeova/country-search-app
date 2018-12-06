package ci.gcorp.countries.manager;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CountriesManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesManagerApplication.class, args);
    }
}
