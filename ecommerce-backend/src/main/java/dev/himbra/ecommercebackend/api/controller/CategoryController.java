package dev.himbra.ecommercebackend.api.controller;

import dev.himbra.ecommercebackend.dto.CategoryRequest;
import dev.himbra.ecommercebackend.mapper.ProductMapper;
import dev.himbra.ecommercebackend.model.Category;
import dev.himbra.ecommercebackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController @RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryRequest categoryRequest) {
        Category saved = categoryRepository.save(productMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

