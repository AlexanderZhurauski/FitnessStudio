package org.mycompany.fitness.dao.repositories;

import org.mycompany.fitness.dao.entities.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;
public interface IUserAuthenticationRepository extends Repository<User, UUID> {
    Optional<User> findUserByMail(String mail);
}
