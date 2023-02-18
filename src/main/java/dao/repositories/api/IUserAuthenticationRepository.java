package dao.repositories.api;

import dao.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
public interface IUserAuthenticationRepository extends CrudRepository<User, UUID> {
}
