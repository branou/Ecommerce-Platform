package dev.himbra.ecommercebackend.mappers;

import dev.himbra.ecommercebackend.model.Brand;
import dev.himbra.ecommercebackend.model.Category;
import dev.himbra.ecommercebackend.model.Image;
import dev.himbra.ecommercebackend.model.SubCategory;
import dev.himbra.ecommercebackend.repository.BrandRepository;
import dev.himbra.ecommercebackend.repository.CategoryRepository;
import dev.himbra.ecommercebackend.repository.ImageRepository;
import dev.himbra.ecommercebackend.repository.SubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;

import java.util.List;

@org.mapstruct.Mapper
@RequiredArgsConstructor
public class Mapper {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;

    @Named("fetchCategoryById")
    public Category fetchCategoryById(Long id) {
        if (id == null) return null;
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }
    @Named("fetchSubCategoryById")
    public SubCategory fetchSubCategoryById(Long id) {
        if (id == null) return null;
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));
    }
    @Named("fetchBrandById")
    public Brand fetchBrandById(Long id) {
        if (id == null) return null;
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
    }
    @Named("fetchImagesByUrls")
    public List<Image> fetchImagesByUrls(List<String> urls) {
        if (urls == null || urls.isEmpty()) return List.of();
        return urls.stream()
                .map(imageRepository::findByUrl).toList();
    }
}
