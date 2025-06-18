package dev.himbra.ecommercebackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class CartItemResponse {
    private ProductResponse product;
    private Integer quantity;
}
