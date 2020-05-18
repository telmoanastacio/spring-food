package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_detail_steps")
public class RecipeDetailSteps implements Serializable
{
    private static final long serialVersionUID = 42865132230018L;

    @EmbeddedId
    private RecipeDetailStepsId recipeDetailStepsId;

    @Column(name = "recipe_option_id", nullable = false)
    private Long recipeOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeDetailId")
    private RecipeDetail recipe_detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stepId")
    private Step step;

    private RecipeDetailSteps() {}

    public RecipeDetailSteps(Long recipeOptionId, RecipeDetail recipeDetail, Step step)
    {
        this.recipeOptionId = recipeOptionId;
        this.recipe_detail = recipeDetail;
        this.step = step;
        this.recipeDetailStepsId = new RecipeDetailStepsId(recipeDetail.getId(), step.getId());
    }

    public RecipeDetailStepsId getRecipeDetailStepsId()
    {
        return recipeDetailStepsId;
    }

    public Long getRecipeOptionId()
    {
        return recipeOptionId;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipe_detail;
    }

    public Step getStep()
    {
        return step;
    }

    public void setRecipeDetailStepsId(RecipeDetailStepsId recipeDetailStepsId)
    {
        this.recipeDetailStepsId = recipeDetailStepsId;
    }

    public void setRecipeOptionId(Long recipeOptionId)
    {
        this.recipeOptionId = recipeOptionId;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipe_detail = recipeDetail;
    }

    public void setStep(Step step)
    {
        this.step = step;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RecipeDetailSteps that = (RecipeDetailSteps) o;
        return Objects.equals(recipeOptionId, that.recipeOptionId) &&
                Objects.equals(recipe_detail, that.recipe_detail) &&
                Objects.equals(step, that.step);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeOptionId, recipe_detail, step);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailSteps{" +
                "recipeDetailStepsId=" + recipeDetailStepsId +
                ", recipeOptionId=" + recipeOptionId +
                ", recipeDetail=" + recipe_detail +
                ", step=" + step +
                '}';
    }
}