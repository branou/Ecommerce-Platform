package dev.himbra.ecommercebackend.api.controller;

import dev.himbra.ecommercebackend.model.Brand;
import dev.himbra.ecommercebackend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandRepository brandRepository;
    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody Brand brand) {
        Brand saved = brandRepository.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
