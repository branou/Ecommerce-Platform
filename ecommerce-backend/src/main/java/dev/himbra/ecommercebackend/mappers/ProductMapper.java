package dev.himbra.ecommercebackend.mappers;

import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.model.Category;
import dev.himbra.ecommercebackend.model.Image;
import dev.himbra.ecommercebackend.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Mapper.class})
public interface ProductMapper {

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "subCategoryName", source = "subCategory.name")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "imageUrls", source = "images",qualifiedByName = "mapImageUrls")
    ProductResponse toProductDTO(Product product);

    @Mapping(target = "category",source = "categoryId",qualifiedByName = "fetchCategoryById")
    @Mapping(target = "subCategory",source = "subCategoryId",qualifiedByName = "fetchSubCategoryById")
    @Mapping(target = "brand",source = "brandId",qualifiedByName = "fetchBrandById")
    @Mapping(target = "images", source = "imageUrls",qualifiedByName = "fetchImagesByUrls")
    Product toProduct(ProductRequest productRequest);

    @Named("mapImageUrls")
     default List<String> mapImageUrls(List<Image> images) {
        if (images == null) return List.of();
        return images.stream()
                .map(Image::getUrl).toList();
    }



}