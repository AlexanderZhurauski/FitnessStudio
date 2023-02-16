package web.controllers;

import core.dto.BaseEssence;
import core.dto.UserDTO;
import core.dto.UserLoginDTO;
import core.dto.UserRegistrationDTO;
import core.dto.enums.UserRole;
import core.dto.enums.UserStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public void verifyCode(@RequestParam String code) {

    }

    @PostMapping("/login")
    public ResponseEntity<String> register(UserLoginDTO userLogin) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
