package dev.himbra.ecommercebackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_categories")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonBackReference
    @OneToMany(mappedBy = "subCategory")
    private List<Product> products = new ArrayList<>();


}
