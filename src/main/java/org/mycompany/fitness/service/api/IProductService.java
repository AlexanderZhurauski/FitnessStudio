package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IProductService {

    void create(ProductCreateDTO productCreateDTO);
    Page<ProductDTO> getPage(Pageable pageable);
    Product getByID(UUID uuid);
    void update(UUID uuid, Instant lastUpdated, ProductCreateDTO productCreateDTO);
}
