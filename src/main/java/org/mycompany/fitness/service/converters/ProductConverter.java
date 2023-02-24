package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.service.converters.api.IEntityConverter;

public class ProductConverter implements IEntityConverter<Product, ProductCreateDTO, ProductDTO> {
    @Override
    public Product convertToEntity(ProductCreateDTO productCreateDTO) {
        Product product = new Product();

        product.setTitle(productCreateDTO.getTitle());
        product.setWeight(productCreateDTO.getWeight());
        product.setCalories(productCreateDTO.getCalories());
        product.setProteins(productCreateDTO.getProteins());
        product.setFats(productCreateDTO.getFats());
        product.setCarbohydrates(productCreateDTO.getCarbohydrates());

        return product;
    }

    @Override
    public ProductDTO convertFromEntity(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BaseEssence baseEssence = new BaseEssence(product.getUuid(),
                product.getCreationTime(), product.getLastUpdated());

        productDTO.setBaseEssence(baseEssence);
        productDTO.setTitle(product.getTitle());
        productDTO.setWeight(product.getWeight());
        productDTO.setCalories(product.getCalories());
        productDTO.setProteins(product.getProteins());
        productDTO.setFats(product.getFats());
        productDTO.setCarbohydrates(product.getCarbohydrates());

        return productDTO;
    }
}
