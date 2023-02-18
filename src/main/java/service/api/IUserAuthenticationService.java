package service.api;

import core.dto.UserDTO;
import core.dto.UserLoginDTO;
import core.dto.UserRegistrationDTO;

import java.util.UUID;

public interface IUserAuthenticationService {

    UUID register(UserRegistrationDTO userRegistrationDTO);
    boolean verify(String code, String mail);
    void login(UserLoginDTO userLoginDTO);
    UserDTO getMyData(UUID uuid);
}
