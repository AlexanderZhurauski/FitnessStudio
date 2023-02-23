package org.mycompany.fitness.web.controllers;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.core.dto.services.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.services.user.UserRegistrationDTO;
import org.mycompany.fitness.core.dto.enums.UserRole;
import org.mycompany.fitness.core.dto.enums.UserStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserAuthenticationController {

    @PostMapping("/registration")
    public ResponseEntity<String> register(UserRegistrationDTO userRegistration) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verifyCode(@RequestParam String code,
                                             @RequestParam String mail) {

        return ResponseEntity.ok("Пользователь верифицирован");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserLoginDTO userLogin) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Вход выполнен");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyData() {
        UserDTO userData = new UserDTO();
        BaseEssence baseEssence = new BaseEssence();
        baseEssence.setCreationTime(Instant.now());
        baseEssence.setLastUpdated(Instant.now());
        baseEssence.setUuid(UUID.randomUUID());

        userData.setBaseEssence(baseEssence);
        userData.setMail("gandalfdude@gmail.com");
        userData.setRole(UserRole.ADMIN);
        userData.setStatus(UserStatus.ACTIVATED);
        userData.setFullName("Alexander ZH");

        return ResponseEntity.ok(userData);
    }
}
