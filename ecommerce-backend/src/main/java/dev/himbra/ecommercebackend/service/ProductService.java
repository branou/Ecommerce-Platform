package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.dto.PageResponse;
import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.mappers.ProductMapper;
import dev.himbra.ecommercebackend.model.*;
import dev.himbra.ecommercebackend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // find product by id
    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
    // A method that handle Add product operation
    public ProductResponse addProduct(ProductRequest product) {

        return productMapper.toProductDTO(
                productRepository.save(productMapper.toProduct(product))
        );
    }

    // A method that handle Update product operation
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
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

    // A method that handle Delete product operation
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.delete(product);
    }
    // A method that handle get product operation
    public ProductResponse getProduct(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
    // A method that handle get all products operation
    public PageResponse<ProductResponse> getAllProducts(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size, Sort.by("createdDate").descending()));
        return new PageResponse<>(
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast(),
                products.map(productMapper::toProductDTO).getContent()
        );
    }




}
