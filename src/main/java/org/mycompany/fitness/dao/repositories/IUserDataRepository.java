package org.mycompany.fitness.dao.repositories;

import org.mycompany.fitness.dao.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;
public interface IUserDataRepository extends CrudRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
    Optional<User> findByMail(String mail);
}
