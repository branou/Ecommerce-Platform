package dev.himbra.ecommercebackend.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;
@Profile("dev")
@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Dotenv dotenv = Dotenv.load();
        return new Cloudinary(Map.of(
                "cloud_name", dotenv.get("CLOUD_NAME"),
                "api_key",    dotenv.get("API_KEY"),
                "api_secret", dotenv.get("API_SECRET")));
    }
}
