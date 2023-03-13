package org.mycompany.fitness.web.controllers;

import jakarta.validation.Valid;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class  UserAuthenticationController {

    private IUserAuthenticationService userAuthenticationService;
    private IUserDataService userDataService;
    private JavaMailSender javaMailSender;

    public UserAuthenticationController(IUserAuthenticationService userAuthenticationService,
                                        IUserDataService userDataService, JavaMailSender javaMailSender) {

        this.userAuthenticationService = userAuthenticationService;
        this.userDataService = userDataService;
        this.javaMailSender = javaMailSender;
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

        this.userAuthenticationService.verify(code, mail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLogin) {

        if (!this.userDataService.isActivated(userLogin.getMail())) {
            throw new BadCredentialsException(userLogin.getMail()
                    + " mail address has not been verified!");
        }
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
