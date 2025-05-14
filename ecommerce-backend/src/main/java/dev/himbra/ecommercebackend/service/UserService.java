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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    // A method that get all the user wishlist products paginated
    public PageResponse<ProductResponse> getWishlistProducts(int page, int size) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }

        Jwt jwt = jwtAuth.getToken();
        String userId = jwt.getClaim("sub");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Page<Product> products = userRepository.findWishlistProductsByUserId(
                user.getId(),
                PageRequest.of(page, size)
        );

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
