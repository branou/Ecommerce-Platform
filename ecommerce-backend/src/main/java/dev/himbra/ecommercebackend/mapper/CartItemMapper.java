package dev.himbra.ecommercebackend.mapper;

import dev.himbra.ecommercebackend.dto.CartItemResponse;
import dev.himbra.ecommercebackend.model.CartItem;

import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    private final ProductMapper productMapper;

    public CartItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .product(productMapper.toProductDTO(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .build();
    }
}
