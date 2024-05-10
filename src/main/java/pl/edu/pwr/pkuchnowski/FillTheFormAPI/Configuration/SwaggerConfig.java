package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FillTheForm API")
                        .version("1.0")
                        .description("This is a simple API to interact with Google Forms via Google Apps Script. It generates - based on the template form - pre-filled form and retrieve its links. It uses Google's API Script and OAuth2 for authentication and execution of pre-fill script that pre-fills Google Form with provided customer and order data."));
    }

}
