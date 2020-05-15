package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Embeddable
public class RecipeDetailStepsId implements Serializable
{
    private static final long serialVersionUID = 42865132230017L;

    @Column(name = "recipe_detail_id")
    private Long recipeDetailId;

    @Column(name = "step_id")
    private Long stepId;

    private RecipeDetailStepsId() {}

    public RecipeDetailStepsId(Long recipeDetailId, Long stepId)
    {
        this.recipeDetailId = recipeDetailId;
        this.stepId = stepId;
    }

    public Long getRecipeDetailId()
    {
        return recipeDetailId;
    }

    public Long getStepId()
    {
        return stepId;
    }

    public void setRecipeDetailId(Long recipeDetailId)
    {
        this.recipeDetailId = recipeDetailId;
    }

    public void setStepId(Long stepId)
    {
        this.stepId = stepId;
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
        RecipeDetailStepsId that = (RecipeDetailStepsId) o;
        return Objects.equals(recipeDetailId, that.recipeDetailId) &&
                Objects.equals(stepId, that.stepId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetailId, stepId);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailStepsId{" +
                "recipeDetailId=" + recipeDetailId +
                ", stepId=" + stepId +
                '}';
    }
}
