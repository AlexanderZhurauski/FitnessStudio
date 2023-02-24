package org.mycompany.fitness.dao.repositories.api;

import org.mycompany.fitness.dao.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;
public interface IUserDataRepository extends CrudRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
}
