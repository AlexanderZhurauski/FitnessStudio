package org.mycompany.fitness.web.controllers;

import org.mycompany.fitness.core.dto.ProductCreateDTO;
import org.mycompany.fitness.core.dto.ProductDTO;
import org.mycompany.fitness.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody ProductCreateDTO product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.productService.create(product));
    }

    @GetMapping
    public Page<ProductDTO> getUserPage(Pageable pageable) {
        return this.productService.getPage(pageable);
    }

    @PutMapping("/{uuid}/dt_update/{lastUpdated}")
    public ResponseEntity<ProductDTO> updateUser(@PathVariable UUID uuid,
                                              @PathVariable Long lastUpdated,
                                              @RequestBody ProductCreateDTO product) {

        return ResponseEntity.ok(this.productService.update(uuid, lastUpdated, product));
    }
}
