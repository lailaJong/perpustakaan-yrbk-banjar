package tugasakhir.library.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tugasakhir.library.config.variable.ApplicationConstant;

import javax.sql.DataSource;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Configuration
public class ApplicationPropertiesConfig {
    @Autowired
    ApplicationProperties applicationProperties;

    @Bean(name = ApplicationConstant.PROPERTIES_NAME)
    public ApplicationProperties loadConfig() {
        return applicationProperties;
    }

}
