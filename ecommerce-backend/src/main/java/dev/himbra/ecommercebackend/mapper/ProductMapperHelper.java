package dev.himbra.ecommercebackend.mapper;

import dev.himbra.ecommercebackend.model.Brand;
import dev.himbra.ecommercebackend.model.Category;
import dev.himbra.ecommercebackend.model.Image;
import dev.himbra.ecommercebackend.model.SubCategory;
import dev.himbra.ecommercebackend.repository.BrandRepository;
import dev.himbra.ecommercebackend.repository.CategoryRepository;
import dev.himbra.ecommercebackend.repository.SubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductMapperHelper {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BrandRepository brandRepository;

    public List<String> mapImageUrls(List<Image> images) {
        if (images == null || images.isEmpty()) {
            System.out.println("No images found, returning empty list");
            return List.of();}
        return images.stream()
                .map(Image::getUrl).toList();
    }

    public String mapToCategoryName(Category category) {
        if (category == null) return null;
        return category.getName();
    }

    public String mapToSubCategoryName(SubCategory subCategory) {
        if (subCategory == null) return null;
        return subCategory.getName();
    }

    public String mapToBrandName(Brand brand) {
        if (brand == null) return null;
        return brand.getName();
    }


    public Category fetchCategoryById(Long id) {
        if (id == null) return null;
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public SubCategory fetchSubCategoryById(Long id) {
        if (id == null) return null;
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));
    }

    public Brand fetchBrandById(Long id) {
        if (id == null) return null;
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
    }

    public List<SubCategory> mapToSubCategoryList(List<Long> ids){
        if (ids == null || ids.isEmpty()) return List.of();
        List<SubCategory> subCategories = new ArrayList<>();
        for(Long id:ids){
            Optional<SubCategory> sub=subCategoryRepository.findById(id);
            sub.ifPresent(subCategories::add);
        }
        return subCategories;
    }

}
