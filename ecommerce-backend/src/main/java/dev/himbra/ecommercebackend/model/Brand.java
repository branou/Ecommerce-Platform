package dev.himbra.ecommercebackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;

    @JsonBackReference
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

}
