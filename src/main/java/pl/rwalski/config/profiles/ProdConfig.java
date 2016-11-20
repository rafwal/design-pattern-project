package pl.rwalski.config.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("prod")
public class ProdConfig {

    @PostConstruct
    private void init() {
        System.out.println("Prod spring profile");
    }
}
