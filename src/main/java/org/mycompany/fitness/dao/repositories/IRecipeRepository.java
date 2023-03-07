package org.mycompany.fitness.dao.repositories;

import org.mycompany.fitness.dao.entities.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IRecipeRepository extends PagingAndSortingRepository<Recipe, UUID>,
        CrudRepository<Recipe, UUID> {
}
