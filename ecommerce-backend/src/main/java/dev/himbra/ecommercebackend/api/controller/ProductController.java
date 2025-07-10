package dev.himbra.ecommercebackend.api.controller;

import dev.himbra.ecommercebackend.dto.PageResponse;
import dev.himbra.ecommercebackend.dto.ProductReq;
import dev.himbra.ecommercebackend.dto.ProductRequest;
import dev.himbra.ecommercebackend.dto.ProductResponse;
import dev.himbra.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    //admin
    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> addProduct( @Valid @RequestPart("product") ProductRequest product,       // JSON part for product data
                                                       @RequestPart("images") List<MultipartFile> images) {
        // Logic to add a product
        return ResponseEntity.ok(productService.addProduct(product,images));
    }
    //this add method will be used for quick add products
    @PostMapping("/addProductQuick")
    public ResponseEntity<ProductResponse> addProductQuick(@Valid @RequestBody ProductReq productRequest) {
        // Logic to add a product quickly
        return ResponseEntity.ok(productService.addProductQuick(productRequest));
    }
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long productId){
        // Logic to update a product
        return ResponseEntity.ok(productService.updateProduct(productRequest, productId));
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Logic to delete a product
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        // Logic to get a product
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Logic to get all products
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }
    @GetMapping("/getAllProductsByCategory/{categoryId}")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProductsByCategory(@PathVariable Long categoryId,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId,page ,size));
    }
    @GetMapping("/getAllProductsBySubCategory/{subCategoryId}")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProductsBySubCategory(@PathVariable Long subCategoryId,
                                                                                     @RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getProductsBySubCategory(subCategoryId, page, size));
    }
    @GetMapping("/getAllProductsByBrand/{brandId}")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProductsByBrand(@PathVariable Long brandId,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getProductsByBrand(brandId, page, size));}
    @GetMapping("/getAllProductsByPromotion")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProductsByPromotion(@RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getPromotedProducts(page, size));}
    @GetMapping("/getAllProductsBySearch/{searchTerm}")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProductsBySearch(@PathVariable String searchTerm,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getProductsByKeyword(searchTerm, page, size));
    }
    @PostMapping("/addToWishlist")
    public void addToWishlist(@RequestBody ProductRequest productRequest){
        productService.addProductToWishlist(productRequest);
    }
    @GetMapping("/getProductsWishlist")
    public List<ProductResponse> getWishlist(){
        return productService.getProductsWishlist();
    }
}
