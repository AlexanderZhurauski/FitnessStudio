package org.mycompany.fitness.web.controllers;

import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

    private IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody RecipeCreateDTO recipe) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.recipeService.create(recipe));
    }

    @GetMapping
    public Page<RecipeDTO> getUserPage(Pageable pageable) {
        return this.recipeService.getPage(pageable);
    }

    @PutMapping("/{uuid}/dt_update/{lastUpdated}")
    public ResponseEntity<RecipeDTO> updateUser(@PathVariable UUID uuid,
                                                 @PathVariable Instant lastUpdated,
                                                 @RequestBody RecipeCreateDTO product) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.recipeService.update(uuid, lastUpdated, product));
    }
}
