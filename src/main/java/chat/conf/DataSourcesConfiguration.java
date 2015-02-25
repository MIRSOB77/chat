package chat.conf;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by Mirsad on 22.02.2015.
 */
@ConfigurationProperties(prefix="modules.myproject.datasource")
public class DataSourcesConfiguration {

    @Bean
    DataSource springDb(){
        DataSource dataSource = DataSourceBuilder.create().driverClassName("org.h2.Driver").build();

        return dataSource;
    }
}
