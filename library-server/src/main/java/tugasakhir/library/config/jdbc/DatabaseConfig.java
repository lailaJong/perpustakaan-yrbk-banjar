package tugasakhir.library.config.jdbc;

import tugasakhir.library.config.variable.ApplicationConstant;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Configuration
public class DatabaseConfig {
    @Bean(name = ApplicationConstant.BEAN_DS)
    @ConfigurationProperties("datasouce.database-server")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
