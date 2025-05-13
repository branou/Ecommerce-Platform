package dev.himbra.ecommercebackend.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        BigDecimal discountedPrice,
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
