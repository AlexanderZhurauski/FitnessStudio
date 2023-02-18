package dao.repositories.api;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IUserDataRepository, IUserAuthenticationRepository{
}
