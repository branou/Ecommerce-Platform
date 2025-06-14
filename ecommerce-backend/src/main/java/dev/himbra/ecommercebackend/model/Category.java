package dev.himbra.ecommercebackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;
    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
