package web.controllers;

import dto.PageOfUserDTO;
import dto.UserCreateDTO;
import dto.UserDTO;
import dto.enums.UserRole;
import dto.enums.UserStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public PageOfUserDTO getUserPage(@RequestParam(defaultValue = "0") long page,
                                     @RequestParam(defaultValue = "20") long size) {
        PageOfUserDTO pageResponse = new PageOfUserDTO();
        UserDTO userData = new UserDTO();

        userData.setCreationTime(Instant.now());
        userData.setLastUpdated(Instant.now());
        userData.setMail("gandalfdude@gmail.com");
        userData.setRole(UserRole.ADMIN);
        userData.setStatus(UserStatus.ACTIVATED);
        userData.setUuid(UUID.randomUUID());
        userData.setFullName("Alexander ZH");

        pageResponse.setContent(userData);
        pageResponse.setFirst(true);
        pageResponse.setLast(false);
        pageResponse.setTotalPages(10);
        pageResponse.setNumber(1);
        pageResponse.setTotalElements(100);
        pageResponse.setSize(10);
        return pageResponse;
    }

    @GetMapping("/{uuid}")
    public UserDTO getUserData(@PathVariable UUID uuid) {
        UserDTO userData = new UserDTO();
        userData.setCreationTime(Instant.now());
        userData.setLastUpdated(Instant.now());
        userData.setMail("gandalfdude@gmail.com");
        userData.setRole(UserRole.ADMIN);
        userData.setStatus(UserStatus.ACTIVATED);
        userData.setUuid(uuid);
        userData.setFullName("Alexander ZH");

        return userData;
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void updateUser(@PathVariable UUID uuid,
                           @PathVariable Instant dt_update,
                           @RequestBody UserCreateDTO user) {

    }
}
