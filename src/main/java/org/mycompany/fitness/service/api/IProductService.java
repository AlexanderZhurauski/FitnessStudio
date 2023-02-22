package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.ProductCreateDTO;
import org.mycompany.fitness.core.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductService {

    UUID create(ProductCreateDTO productCreateDTO);
    Page<ProductDTO> getPage(Pageable pageable);
    ProductDTO update(UUID uuid, Long lastUpdated, ProductCreateDTO productCreateDTO);
}
