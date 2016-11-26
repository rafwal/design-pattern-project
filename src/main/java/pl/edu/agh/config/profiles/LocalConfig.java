package pl.edu.agh.config.profiles;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

@Configuration
@Profile("local")
public class LocalConfig {

    @PostConstruct
    private void init() {
        System.out.println("Local spring profile");
    }

    @Bean
    //creates data source for h2 db, url, password and user are required fields
    public DataSource dataSource() throws SQLException {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();

        jdbcDataSource.setUrl("jdbc:h2:mem:loading-tests;DB_CLOSE_DELAY=-1;MODE=postgresql");
        jdbcDataSource.setUser("user");
        jdbcDataSource.setPassword("password");

        initDb(jdbcDataSource);

        return jdbcDataSource;
    }


    private void initDb(DataSource dataSource) throws SQLException {
        InputStream resourceAsStream = LocalConfig.class.getResourceAsStream("/schema.sql");
        RunScript.execute(dataSource.getConnection(), new InputStreamReader(resourceAsStream));
    }


    @Bean(destroyMethod = "stop", initMethod = "start")
    @Autowired
    public Server h2webServer(DataSource dataSource) throws SQLException {
        //just to make sure dataSource really is created
        return Server.createWebServer("-web", "-webPort", "12321", "-webAllowOthers");
    }

}
