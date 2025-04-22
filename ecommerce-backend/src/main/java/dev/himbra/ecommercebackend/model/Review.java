package dev.himbra.ecommercebackend.model;

import dev.himbra.ecommercebackend.config.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review extends BaseEntity {
    @Id
    private String id;
    private String userId; // Refers to Keycloak ID or internal User ID
    private String productId;
    private int rating; // 1-5
    private String comment;

}
