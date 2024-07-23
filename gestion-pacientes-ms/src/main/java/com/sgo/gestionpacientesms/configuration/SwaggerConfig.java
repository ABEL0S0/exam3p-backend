package com.sgo.gestionpacientesms.configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion Pacientes")
                        .version("1.0")
                        .description("Crea, actualiza, elimina, y obtiene a los pacientes de la base de datos"));
    }
}
