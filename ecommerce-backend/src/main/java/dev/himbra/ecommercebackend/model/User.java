package dev.himbra.ecommercebackend.model;
import dev.himbra.ecommercebackend.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class User extends BaseEntity {

    @Id
    private String id; // Store Keycloak UUID as the primary key

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToOne(mappedBy = "user")
    private Wishlist wishlist;

}