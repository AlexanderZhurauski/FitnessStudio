package org.mycompany.fitness.core.dto.services.recipe;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mycompany.fitness.core.dto.BaseEssence;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {

    @JsonUnwrapped
    private BaseEssence baseEssence;
    private String title;
    private List<RecipeCompositionDTO> composition;

    public RecipeDTO() {
    }

    public RecipeDTO(BaseEssence baseEssence, String title,
                     List<RecipeCompositionDTO> composition) {
        this.baseEssence = baseEssence;
        this.title = title;
        this.composition = composition;
    }

    public BaseEssence getBaseEssence() {
        return baseEssence;
    }

    public void setBaseEssence(BaseEssence baseEssence) {
        this.baseEssence = baseEssence;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecipeCompositionDTO> getComposition() {
        return this.composition != null ? this.composition : new ArrayList<>();
    }

    public void setComposition(List<RecipeCompositionDTO> composition) {
        this.composition = composition;
    }

    public void addRecipe(RecipeCompositionDTO recipe) {
        if (this.composition == null) {
            this.composition = new ArrayList<>();
        }
        this.composition.add(recipe);
    }

    public void deleteRecipe(RecipeCompositionDTO recipe) {
        if (this.composition == null) {
            this.composition = new ArrayList<>();
            return;
        }
        this.composition.remove(recipe);
    }
}
