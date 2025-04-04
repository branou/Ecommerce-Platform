package dev.himbra.ecommercebackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String sku;

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

    private String color;

    // 🔹 Stock & Status
    private Integer stockQuantity;
    private Boolean inStock;

    // 🔹 Images
    @ElementCollection
    private List<String> images;

    // 🔹 Timestamps
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
