package dev.himbra.ecommercebackend.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Profile("prod")
@Configuration
public class CloudinaryConfiguration {
    @Value("${cloudinary.cloud-name}") String cloudName;
    @Value("${cloudinary.api-key}")    String apiKey;
    @Value("${cloudinary.api-secret}") String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", cloudName,
                "api_key",    apiKey,
                "api_secret", apiSecret));
    }
}
