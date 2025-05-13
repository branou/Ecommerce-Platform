package dev.himbra.ecommercebackend.repository;

import dev.himbra.ecommercebackend.model.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")

    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.subCategory.id = :subCategoryId")
    Page<Product> findBySubCategoryId(@Param("subCategoryId") Long subCategoryId, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")
    Page<Product> findByBrandId(@Param("brandId") Long brandId, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.isPromoted = true AND p.inStock = true")
    Page<Product> findPromotedProducts(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.inStock = true")
    Page<Product> findInStockProducts(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.inStock = false")
    Page<Product> findOutOfStockProducts(Pageable pageable);


}
