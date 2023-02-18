package dao.repositories.api;

import dao.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserDataRepository extends PagingAndSortingRepository<User, UUID> {
}
