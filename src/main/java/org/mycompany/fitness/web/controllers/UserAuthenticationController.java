package org.mycompany.fitness.web.controllers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserAuthenticationController {

    private IUserAuthenticationService userAuthenticationService;
    private JavaMailSender javaMailSender;

    public UserAuthenticationController(IUserAuthenticationService userAuthenticationService, JavaMailSender javaMailSender) {

        this.userAuthenticationService = userAuthenticationService;
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
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLogin) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("itacademy.team6@mail.ru");
        helper.setTo("gandalfdude@gmail.com");
        helper.setSubject("Test Mail");
        helper.setText("Really it's just a test lol");

        javaMailSender.send(message);
        //String jwtToken = this.userAuthenticationService.login(userLogin);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
                //.body(jwtToken);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyData() {

        UserDTO userData = this.userAuthenticationService.getMyData();
        return ResponseEntity.ok(userData);
    }
}
