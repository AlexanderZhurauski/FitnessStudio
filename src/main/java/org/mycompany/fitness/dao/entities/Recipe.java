package org.mycompany.fitness.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "recipe")
public class Recipe {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID uuid;
    @Column(name = "creation_time", nullable = false)
    private Instant creationTime = Instant.now();
    @Column(name = "last_updated", nullable = false)
    @Version
    private Instant lastUpdated = Instant.now();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            schema = "app",
            name = "recipe_product",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "product_instance_id"))
    private List<ProductInstance> composition;
    @NotBlank
    @NotNull
    private String title;

    public Recipe() {
    }

    public Recipe(UUID uuid, Instant creationTime,
                  Instant lastUpdated, String title,
                  List<ProductInstance> composition) {
        this.uuid = uuid;
        this.creationTime = creationTime;
        this.lastUpdated = lastUpdated;
        this.title = title;
        this.composition = composition;
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

    public List<ProductInstance> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductInstance> composition) {
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}