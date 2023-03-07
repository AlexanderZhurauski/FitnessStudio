package org.mycompany.fitness.service;

import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.security.UserHolder;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserAuthenticationService implements IUserAuthenticationService {

    private UserDetailsManager userManager;
    private UserHolder userHolder;
    private Converter<User, UserDTO> toDTOConverter;

    public UserAuthenticationService(UserDetailsManager userManager,
                                     UserHolder userHolder,
                                     Converter<User, UserDTO> toDTOConverter) {

        this.userManager = userManager;
        this.userHolder = userHolder;
    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {
    }

    @Override
    public void verify(String code, String mail) {
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
    }
    @Override
    public UserDTO getMyData() {
        User user = (User) this.userHolder.getAuthentication().getPrincipal();
        return toDTOConverter.convert(user);
    }
}
