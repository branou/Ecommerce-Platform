package dev.himbra.ecommercebackend.service;

import dev.himbra.ecommercebackend.dto.PageResponse;
import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.mapper.ProductMapper;
import dev.himbra.ecommercebackend.model.*;
import dev.himbra.ecommercebackend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BrandRepository brandRepository;
    // A method that handle Add product operation
    public ProductResponse addProduct(ProductRequest productRequest,List<MultipartFile> images) {
        Product product = productMapper.toProduct(productRequest);
        List<Image> imageEntities = images.stream()
                .map(file -> {
                    // Save the file (e.g., locally or to S3)
                    String imageUrl = fileStorageService.storeFile(file); // <-- your logic
                    Image image = new Image();
                    image.setUrl(imageUrl);
                    image.setAltText(file.getOriginalFilename()); // or other logic
                    image.setProduct(product);
                    return image;
                }).toList();
        product.setImages(imageEntities);

        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        SubCategory subCategory = subCategoryRepository.findById(productRequest.subCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));
        Brand brand = brandRepository.findById(productRequest.brandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        if (!category.getProducts().contains(product)) {
            category.getProducts().add(product);
        }
        if (!subCategory.getProducts().contains(product)) {
            subCategory.getProducts().add(product);
        }
        if (!category.getSubCategories().contains(subCategory)) {
            category.getSubCategories().add(subCategory);
        }
        subCategory.setCategory(category);
        if (!brand.getProducts().contains(product)) {
            brand.getProducts().add(product);
        }

        return productMapper.toProductDTO(
                productRepository.save(product)
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
    // A method that handle get product by category operation
    public PageResponse<ProductResponse> getProductsByCategory(Long categoryId, int page, int size) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, PageRequest.of(page, size));
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
    // A method that handle get product by sub category operation
    public PageResponse<ProductResponse> getProductsBySubCategory(Long subCategoryId, int page, int size) {
        Page<Product> products = productRepository.findBySubCategoryId(subCategoryId, PageRequest.of(page, size));
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
    // A method that handle get product by brand operation
    public PageResponse<ProductResponse> getProductsByBrand(Long brandId, int page, int size) {
        Page<Product> products = productRepository.findByBrandId(brandId, PageRequest.of(page, size));
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
    // A method that handle get product by keyword operation
    public PageResponse<ProductResponse> getProductsByKeyword(String keyword, int page, int size) {
        Page<Product> products = productRepository.searchByKeyword(keyword, PageRequest.of(page, size));
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
    // A method that handle get product by promoted operation
    public PageResponse<ProductResponse> getPromotedProducts(int page, int size) {
        Page<Product> products = productRepository.findPromotedProducts(PageRequest.of(page, size));
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
    // A method that handle get product by in stock operation
    public PageResponse<ProductResponse> getInStockProducts(int page, int size) {
        Page<Product> products = productRepository.findInStockProducts(PageRequest.of(page, size));
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
