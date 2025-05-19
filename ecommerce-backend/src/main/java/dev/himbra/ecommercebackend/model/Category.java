package dev.himbra.ecommercebackend.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "categories")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
