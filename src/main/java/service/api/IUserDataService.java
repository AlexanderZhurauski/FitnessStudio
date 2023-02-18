package service.api;

import core.dto.PageOfUserDTO;
import core.dto.UserCreateDTO;
import core.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserDataService {

    UUID create(UserCreateDTO userCreateDTO);
    PageOfUserDTO getPage(Pageable pageable);
    UserDTO get(UUID uuid);
    UserDTO update(UUID uuid, Long lastUpdated, UserCreateDTO userCreateDTO);
}
