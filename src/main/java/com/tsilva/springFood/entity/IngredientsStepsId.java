package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Embeddable
public class IngredientsStepsId implements Serializable
{
    private static final long serialVersionUID = 42865132230019L;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "step_id")
    private Long stepId;

    private IngredientsStepsId() {}

    public IngredientsStepsId(Long ingredientId, Long stepId)
    {
        this.ingredientId = ingredientId;
        this.stepId = stepId;
    }

    public Long getIngredientId()
    {
        return ingredientId;
    }

    public Long getStepId()
    {
        return stepId;
    }

    public void setIngredientId(Long ingredientId)
    {
        this.ingredientId = ingredientId;
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
        IngredientsStepsId that = (IngredientsStepsId) o;
        return Objects.equals(ingredientId, that.ingredientId) &&
                Objects.equals(stepId, that.stepId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ingredientId, stepId);
    }

    @Override
    public String toString()
    {
        return "IngredientsStepsId{" +
                "ingredientId=" + ingredientId +
                ", stepId=" + stepId +
                '}';
    }
}
