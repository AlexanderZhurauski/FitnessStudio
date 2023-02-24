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
    @Positive
    private int calories;
    @Positive
    private double proteins;
    @Positive
    private double fats;
    @Positive
    private double carbohydrates;

    public ProductInstance() {
    }

    public ProductInstance(UUID uuid, Product product, int weight,
                           int calories, double proteins,
                           double fats, double carbohydrates) {
        this.uuid = uuid;
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
