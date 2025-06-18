package dev.himbra.ecommercebackend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
public record ProductRequest(
        @NotBlank String name,
        String description,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull @Min(0) Integer stockQuantity,
        Boolean isPromoted,
        @Min(0) @Max(100) Integer discountPercent,
        @NotNull Long categoryId,
        Long subCategoryId,
        Long brandId
) {
}
