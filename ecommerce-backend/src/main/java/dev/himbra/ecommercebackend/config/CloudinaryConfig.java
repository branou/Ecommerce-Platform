package dev.himbra.ecommercebackend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "dm9n3j6ql",
                "api_key",    "552823524462288",
                "api_secret", "MWYQNxR9A0kb-XBGgVGdM4U92N8"));
    }
}
