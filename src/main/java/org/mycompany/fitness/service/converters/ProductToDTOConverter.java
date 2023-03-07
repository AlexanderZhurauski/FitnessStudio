package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.springframework.core.convert.converter.Converter;

public class ProductToDTOConverter implements Converter<Product, ProductDTO> {
    @Override
    public ProductDTO convert(Product product) {

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
