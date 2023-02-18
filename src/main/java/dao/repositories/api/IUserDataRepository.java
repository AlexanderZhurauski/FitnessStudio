package dao.repositories.api;

import dao.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;
public interface IUserDataRepository extends PagingAndSortingRepository<User, UUID> {
}
