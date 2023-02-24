package org.mycompany.fitness.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import org.mycompany.fitness.core.dto.converters.InstantToUnix;
import org.mycompany.fitness.core.dto.converters.UnixToInstant;

import java.time.Instant;
import java.util.UUID;
public class BaseEssence {
    @NotNull
    private UUID uuid;
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    @NotNull
    private Instant creationTime;
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    @NotNull
    private Instant lastUpdated;

    public BaseEssence() {
    }

    public BaseEssence(UUID uuid, Instant creationTime, Instant lastUpdated) {
        this.uuid = uuid;
        this.creationTime = creationTime;
        this.lastUpdated = lastUpdated;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("dt_create")
    public Instant getCreationTime() {
        return creationTime;
    }

    @JsonProperty("dt_create")
    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    @JsonProperty("dt_update")
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("dt_update")
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
