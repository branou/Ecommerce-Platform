package dev.himbra.ecommercebackend.api.controller;

import dev.himbra.ecommercebackend.model.SubCategory;
import dev.himbra.ecommercebackend.repository.SubCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryController(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @PostMapping
    public ResponseEntity<SubCategory> create(@RequestBody SubCategory subCategory) {
        SubCategory saved = subCategoryRepository.save(subCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<SubCategory> getAll() {
        return subCategoryRepository.findAll();
    }
}