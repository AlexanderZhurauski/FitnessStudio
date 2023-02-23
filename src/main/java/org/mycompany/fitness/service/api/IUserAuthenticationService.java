package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.core.dto.services.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.services.user.UserRegistrationDTO;

import java.util.UUID;

public interface IUserAuthenticationService {

    UUID register(UserRegistrationDTO userRegistrationDTO);
    boolean verify(String code, String mail);
    void login(UserLoginDTO userLoginDTO);
    UserDTO getMyData(UUID uuid);
}
