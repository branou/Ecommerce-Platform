package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.mappers.ProductMapper;
import dev.himbra.ecommercebackend.model.*;
import dev.himbra.ecommercebackend.repository.*;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    // find product by id
    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    // A method that handle Add product operation
    public ProductResponse addProduct(ProductRequest product) {

        return productMapper.toProductDTO(
                productRepository.save(productMapper.toProduct(product))
        );
    }

    // A methods that handle Update product operation
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product productMapped = productMapper.toProduct(productRequest);
        product.setName(productMapped.getName());
        product.setDescription(productMapped.getDescription());
        product.setPrice(productMapped.getPrice());
        product.setCategory(productMapped.getCategory());
        product.setSubCategory(productMapped.getSubCategory());
        product.setBrand(productMapped.getBrand());
        product.setImages(productMapped.getImages());
        product.setStockQuantity(productMapped.getStockQuantity());
        product.setIsPromoted(productMapped.getIsPromoted());
        product.setDiscountPercent(productMapped.getDiscountPercent());
        return productMapper.toProductDTO(
                productRepository.save(product)
        );
    }

    // A methods that handle Delete product operation
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
    // A methods that handle get product operation
    public ProductResponse getProduct(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    // Add methods that handle get all products operation


}
