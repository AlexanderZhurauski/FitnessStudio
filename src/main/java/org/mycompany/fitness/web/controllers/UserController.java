
package org.mycompany.fitness.web.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.mycompany.fitness.core.dto.services.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private IUserDataService service;

    public UserController(IUserDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateDTO user) {

        this.service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
    @GetMapping
    public Page<UserDTO> getUserPage(Pageable pageable) {

        return this.service.getPage(pageable);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserData(@PathVariable UUID uuid) {

        return ResponseEntity.ok(this.service.get(uuid));
    }

    @PutMapping("/{uuid}/dt_update/{lastUpdated}")
    public ResponseEntity<String> updateUser(@PathVariable UUID uuid,
                                              @PathVariable @Positive Long lastUpdated,
                                             @Valid @RequestBody UserCreateDTO user) {

        this.service.update(uuid, lastUpdated, user);
        return ResponseEntity.ok()
                .build();
    }
}