package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<Image, Long> {
     Image findByUrl(String url);
}
