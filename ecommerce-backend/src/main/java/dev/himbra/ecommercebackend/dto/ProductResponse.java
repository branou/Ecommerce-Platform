package dev.himbra.ecommercebackend.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        Boolean inStock,
        Boolean isPromoted,
        Integer discountPercent,
        String slug,
        String categoryName,
        String subCategoryName,
        String brandName,
        List<String> imageUrls
) {
}
