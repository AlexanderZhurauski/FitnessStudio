package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IUserDataService {

    void create(UserCreateDTO userCreateDTO);
    Page<UserDTO> getPage(Pageable pageable);
    UserDTO get(UUID uuid);
    void update(UUID uuid, Instant lastUpdated, UserCreateDTO userCreateDTO);
}
