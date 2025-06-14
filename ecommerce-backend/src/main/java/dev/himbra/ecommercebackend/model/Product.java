package dev.himbra.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.himbra.ecommercebackend.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Product extends BaseEntity {

    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean inStock;
    private Boolean isPromoted;
    private Integer discountPercent;
    @Column(nullable = false, unique = true)
    private String slug;

    //RELATIONSHIPS
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
    @JsonManagedReference
    @ManyToMany(mappedBy = "product")
    private List<Wishlist> wishlists;

   @Transient
    public BigDecimal getDiscountedPrice() {
        if (discountPercent != null && discountPercent > 0) {
            return price.subtract(price.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100)));
        }
        return price;
    }
    @PrePersist
    @PreUpdate
    public void preSave() {
        // Stock logic
        this.inStock = stockQuantity != null && stockQuantity > 0;

        // Slug logic
        if (name != null && (slug == null || slug.isEmpty())) {
            this.slug = name.toLowerCase()
                    .replaceAll("[^a-z0-9\\s]", "")  // Remove non-alphanumeric characters
                    .replaceAll("\\s+", "-")         // Replace spaces with dashes
                    .replaceAll("-+$", "");           // Remove trailing dashes
        }
    }

}
