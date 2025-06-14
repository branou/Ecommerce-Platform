package dev.himbra.ecommercebackend.mapper;

import dev.himbra.ecommercebackend.dto.CategoryRequest;
import dev.himbra.ecommercebackend.dto.ProductReq;
import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.model.Category;
import dev.himbra.ecommercebackend.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductMapperHelper productMapperHelper;
    public ProductResponse toProductDTO(Product product){
        return ProductResponse.builder().id(product.getId())
                .name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).stockQuantity(product.getStockQuantity())
                .inStock(product.getInStock()).isPromoted(product.getIsPromoted())
                .discountPercent(product.getDiscountPercent())
                .slug(product.getSlug())
                .categoryName(productMapperHelper.mapToCategoryName(product.getCategory()))
                .subCategoryName(productMapperHelper.mapToSubCategoryName(product.getSubCategory()))
                .brandName(productMapperHelper.mapToBrandName(product.getBrand()))
                .imageUrls(productMapperHelper.mapImageUrls(product.getImages()))
                .build();
    }
    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .stockQuantity(productRequest.stockQuantity())
                .isPromoted(productRequest.isPromoted())
                .discountPercent(productRequest.discountPercent())
                .category(productMapperHelper.fetchCategoryById(productRequest.categoryId()))
                .subCategory(productMapperHelper.fetchSubCategoryById(productRequest.subCategoryId()))
                .brand(productMapperHelper.fetchBrandById(productRequest.brandId()))
                .build();
    }
    public Product toProduct(ProductReq productRequest){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .stockQuantity(productRequest.stockQuantity())
                .category(productMapperHelper.fetchCategoryById(productRequest.categoryId()))
                .subCategory(productMapperHelper.fetchSubCategoryById(productRequest.subCategoryId()))
                .brand(productMapperHelper.fetchBrandById(productRequest.brandId()))
                .build();
    }
    public Category toCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .subCategories(productMapperHelper.mapToSubCategoryList(categoryRequest.SubCategoryIds()))
                .build();
    }
}
