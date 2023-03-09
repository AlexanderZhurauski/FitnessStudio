package org.mycompany.fitness.dao.repositories;

import org.mycompany.fitness.dao.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface IConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
}
