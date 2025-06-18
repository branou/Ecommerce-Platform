package dev.himbra.ecommercebackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor @Builder
public class CartItem {
    @Id @GeneratedValue
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;
}
