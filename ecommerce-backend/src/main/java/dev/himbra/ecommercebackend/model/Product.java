package dev.himbra.ecommercebackend.model;

import dev.himbra.ecommercebackend.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String color;
    private Integer stockQuantity;
    private Boolean inStock;

    //RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_tailles", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "taille")
    @Enumerated(EnumType.STRING)
    private List<Taille> tailles;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

}
