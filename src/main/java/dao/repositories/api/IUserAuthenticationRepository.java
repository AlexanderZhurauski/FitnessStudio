package dao.repositories.api;

import dao.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserAuthenticationRepository extends CrudRepository<User, UUID> {
}
