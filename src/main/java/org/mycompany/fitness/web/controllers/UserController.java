
package org.mycompany.fitness.web.controllers;

import org.mycompany.fitness.core.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private IUserDataService service;

    public UserController(IUserDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.service.create(user));
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
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID uuid,
                                             @PathVariable Long lastUpdated,
                                             @RequestBody UserCreateDTO user) {

        return ResponseEntity.ok(this.service.update(uuid, lastUpdated, user));
    }
}