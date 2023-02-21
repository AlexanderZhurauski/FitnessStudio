
package org.mycompany.fitness.web.controllers;

import org.mycompany.fitness.core.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<UserDTO> getUserPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "20") int size) {
        Pageable pageParams = PageRequest.of(page, size);
        return this.service.getPage(pageParams);
    }

    @GetMapping("/{uuid}")
    public UserDTO getUserData(@PathVariable UUID uuid) {
        return this.service.get(uuid);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID uuid,
                                             @PathVariable Long dt_update,
                                             @RequestBody UserCreateDTO user) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.update(uuid, dt_update, user));
    }
}