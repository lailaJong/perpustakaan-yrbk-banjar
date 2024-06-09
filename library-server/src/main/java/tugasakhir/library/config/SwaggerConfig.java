package tugasakhir.library.config;

/**
 * @author Putri Mele
 * on 09/06/2024
 */
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tugasakhir.library.config.variable.ApplicationConstant;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title(ApplicationConstant.APP_NAME)
                        .version(ApplicationConstant.APP_VERSION));
    }
}
