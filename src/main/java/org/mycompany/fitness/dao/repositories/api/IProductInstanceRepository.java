package org.mycompany.fitness.dao.repositories.api;

import org.mycompany.fitness.dao.entities.ProductInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IProductInstanceRepository extends CrudRepository<ProductInstance, UUID> {
}
