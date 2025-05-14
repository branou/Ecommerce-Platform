package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.dto.PageResponse;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.mappers.ProductMapper;
import dev.himbra.ecommercebackend.model.Product;
import dev.himbra.ecommercebackend.model.User;
import dev.himbra.ecommercebackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    // A method that handle Add user operation
    public PageResponse<ProductResponse> getWishlistProducts(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Page<Product> products=userRepository.findWishlistProductsByUserId(user.getId(), PageRequest.of(page, size));
        return new PageResponse<>(
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast(),
                products.map(productMapper::toProductDTO).getContent()
        );
    }
    // A method that handle Update user operation

}
