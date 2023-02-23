package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.repositories.api.IProductRepository;
import org.mycompany.fitness.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ProductService implements IProductService {

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public UUID create(ProductCreateDTO productCreateDTO) {
        Product product = new Product(productCreateDTO);
        return this.productRepository.save(product).getUuid();
    }

    @Override
    public Page<ProductDTO> getPage(Pageable pageable) {
        Page<Product> productPage = this.productRepository.findAll(pageable);
        return productPage.map(ProductDTO::new);
    }

    @Override
    public Product getByID(UUID uuid) {
        return this.productRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "product"));
    }

    @Override
    public ProductDTO update(UUID uuid, Long lastUpdated,
                             ProductCreateDTO productCreateDTO) {

        Product product = this.productRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "product"));

        if (product.getLastUpdated().toEpochMilli() != lastUpdated) {
            throw new OptimisticLockException("Product with id '" + product.getUuid()
                    + "' has already been modified!");
        }

        product.setTitle(productCreateDTO.getTitle());
        product.setCarbohydrates(productCreateDTO.getCarbohydrates());
        product.setFats(productCreateDTO.getFats());
        product.setProteins(productCreateDTO.getProteins());
        product.setCalories(productCreateDTO.getCalories());
        product.setWeight(productCreateDTO.getWeight());
        this.productRepository.save(product);

        return new ProductDTO(product);
    }
}
