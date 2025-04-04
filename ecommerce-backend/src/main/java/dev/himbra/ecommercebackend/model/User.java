package dev.himbra.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    private String id; // Store Keycloak UUID as the primary key

    private String email;

    private String firstName;

    private String lastName;

    private String role; // Optional, can mirror Keycloak roles

    private LocalDateTime createdAt;

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}