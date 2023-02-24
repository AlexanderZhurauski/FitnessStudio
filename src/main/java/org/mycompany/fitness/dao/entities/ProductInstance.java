package org.mycompany.fitness.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.UUID;
@Entity
@Table(schema = "app", name = "product_instance")
public class ProductInstance {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    private Product product;
    @Positive
    private int weight;

    public ProductInstance() {
    }

    public ProductInstance(UUID uuid, Product product, int weight) {
        this.uuid = uuid;
        this.product = product;
        this.weight = weight;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}