package org.mycompany.fitness.dao.repositories.api;

import org.mycompany.fitness.dao.entities.User;
import org.springframework.data.repository.Repository;

import java.util.UUID;
public interface IUserAuthenticationRepository extends Repository<User, UUID> {
}
