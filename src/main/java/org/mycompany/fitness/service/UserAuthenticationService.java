package org.mycompany.fitness.service;

import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.dao.repositories.IUserAuthenticationRepository;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public class UserAuthenticationService implements IUserAuthenticationService, UserDetailsService {

    private IUserAuthenticationRepository userRepository;

    public UserAuthenticationService(IUserAuthenticationRepository userRepository) {
        this.userRepository = userRepository;
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
    public UserDTO getMyData(UUID uuid) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email '"
                        + username + "' has been found!"));
    }
}
