package service;

import core.dto.UserDTO;
import core.dto.UserLoginDTO;
import core.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;
import service.api.IUserAuthenticationService;
import service.api.IUserDataService;

import java.util.UUID;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {

    private IUserDataService userDataService;

    public UserAuthenticationService(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public UUID register(UserRegistrationDTO userRegistrationDTO) {
        return null;
    }

    @Override
    public boolean verify(String code, String mail) {
        return false;
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {

    }

    @Override
    public UserDTO getMyData(UUID uuid) {
        return this.userDataService.get(uuid);
    }
}
