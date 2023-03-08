package org.mycompany.fitness.web.controllers;

import jakarta.validation.Valid;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserAuthenticationController {

    private IUserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(IUserAuthenticationService userAuthenticationService) {

        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDTO userRegistration) {

        this.userAuthenticationService.register(userRegistration);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verifyCode(@RequestParam String code,
                                             @RequestParam String mail) {

        //TODO: implement mail verification functionality
        return ResponseEntity.ok("User successfully verified");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLogin) {

        String jwtToken = this.userAuthenticationService.login(userLogin);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jwtToken);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyData() {

        UserDTO userData = this.userAuthenticationService.getMyData();
        return ResponseEntity.ok(userData);
    }
}
