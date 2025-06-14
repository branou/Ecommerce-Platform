package dev.himbra.ecommercebackend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProductReq(
        @NotBlank String name,
        String description,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull @Min(0) Integer stockQuantity,
        @NotNull Long categoryId,
        Long subCategoryId,
        Long brandId,
        String imageUrl
) {
}
