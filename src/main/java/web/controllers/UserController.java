package web.controllers;

import core.dto.*;
import core.dto.enums.UserRole;
import core.dto.enums.UserStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Пользователь добавлен");
    }

    @GetMapping
    public PageOfUserDTO getUserPage(Pageable pageParams) {
        PageOfUserDTO pageResponse = new PageOfUserDTO();
        UserDTO userData = new UserDTO();
        PageEssence pageEssence = new PageEssence();

        userData.setMail("gandalfdude@gmail.com");
        userData.setRole(UserRole.ADMIN);
        userData.setStatus(UserStatus.ACTIVATED);
        userData.setFullName("Alexander ZH");
        pageResponse.setContent(List.of(userData));

        pageEssence.setFirst(!pageParams.hasPrevious());
        pageEssence.setLast(false);
        pageEssence.setTotalPages(10);
        pageEssence.setNumber(pageParams.getPageNumber());
        pageEssence.setTotalElements(100);
        pageEssence.setSize(pageParams.getPageSize());
        pageResponse.setPageEssence(pageEssence);

        return pageResponse;
    }

    @GetMapping("/{uuid}")
    public UserDTO getUserData(@PathVariable UUID uuid) {
        UserDTO userData = new UserDTO();
        BaseEssence baseEssence = new BaseEssence();
        baseEssence.setCreationTime(Instant.now());
        baseEssence.setLastUpdated(Instant.now());
        baseEssence.setUuid(uuid);

        userData.setBaseEssence(baseEssence);
        userData.setMail("gandalfdude@gmail.com");
        userData.setRole(UserRole.ADMIN);
        userData.setStatus(UserStatus.ACTIVATED);
        userData.setFullName("Alexander ZH");

        return userData;
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateUser(@PathVariable UUID uuid,
                                             @PathVariable Long dt_update,
                                             @RequestBody UserCreateDTO user) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Счёт обновлён");
    }
}
