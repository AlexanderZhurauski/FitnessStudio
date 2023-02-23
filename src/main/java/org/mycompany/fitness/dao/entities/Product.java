package org.mycompany.fitness.dao.entities;

import jakarta.persistence.*;
import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "product")
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID uuid;
    @Column(name = "creation_time", nullable = false)
    private Instant creationTime = Instant.now();
    @Column(name = "last_updated", nullable = false)
    @Version
    private Instant lastUpdated = Instant.now();
    private String title;
    private int weight;
    private int calories;
    private double proteins;
    private double fats;
    private double carbohydrates;

    public Product() {
    }

    public Product(String title, int weight, int calories,
                   double proteins, double fats, double carbohydrates) {
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Product(ProductCreateDTO productCreateDTO) {
        this.title = productCreateDTO.getTitle();
        this.weight = productCreateDTO.getWeight();
        this.calories = productCreateDTO.getCalories();
        this.proteins = productCreateDTO.getProteins();
        this.fats = productCreateDTO.getFats();
        this.carbohydrates = productCreateDTO.getCarbohydrates();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return weight == product.weight
                && calories == product.calories
                && Double.compare(product.proteins, proteins) == 0
                && Double.compare(product.fats, fats) == 0
                && Double.compare(product.carbohydrates, carbohydrates) == 0
                && uuid.equals(product.uuid)
                && creationTime.equals(product.creationTime)
                && lastUpdated.equals(product.lastUpdated)
                && title.equals(product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, creationTime, lastUpdated, title,
                weight, calories, proteins, fats, carbohydrates);
    }
}
