package pl.edu.agh.app.config.profiles;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ProfileActivator implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String SERVICE_ENVIRONMENT = "SERVICE_ENVIRONMENT";
    private static final String DEFAULT_PROFILE = "local";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        //extracts env variable from system, like when we put in bash export SERVICE_ENVIRONMENT=...
        String env = System.getenv(SERVICE_ENVIRONMENT);
        //extracts variable passed do jvm, like with -D parameter
        String property = System.getProperty(SERVICE_ENVIRONMENT);


        //we activate given profile based on extracted variable, or just default
        String activeProfile;
        if (env != null) {
            activeProfile = env;
        } else if (property != null) {
            activeProfile = property;
        } else {
            activeProfile = DEFAULT_PROFILE;
        }

        //before beans initializations, we set profile on Environment object
        applicationContext.getEnvironment().setActiveProfiles(activeProfile);
    }
}
