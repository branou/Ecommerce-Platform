package dev.himbra.ecommercebackend.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
public record CategoryRequest(
        @NotBlank
        String name,
        List<Long> SubCategoryIds
) { }
