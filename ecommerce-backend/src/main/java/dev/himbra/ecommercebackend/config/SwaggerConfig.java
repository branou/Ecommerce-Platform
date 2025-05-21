package dev.himbra.ecommercebackend.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("ShopPara API's")
                        .description("ShopPara E-commerce Application APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("BRAHIM ANOUGMAR")));
    }
}