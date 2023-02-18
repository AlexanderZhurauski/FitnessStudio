package web.controllers;

import core.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.api.IUserDataService;

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
    public PageOfUserDTO getUserPage(Pageable pageParams) {
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
