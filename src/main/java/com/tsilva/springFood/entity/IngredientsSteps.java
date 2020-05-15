package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "ingredients_steps")
public class IngredientsSteps implements Serializable
{
    private static final long serialVersionUID = 42865132230020L;

    @EmbeddedId
    private IngredientsStepsId ingredientsStepsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stepId")
    private Step step;

    private IngredientsSteps() {}

    public IngredientsSteps(Ingredient ingredient, Step step)
    {
        this.ingredient = ingredient;
        this.step = step;
        this.ingredientsStepsId = new IngredientsStepsId(ingredient.getId(), step.getId());
    }

    public IngredientsStepsId getIngredientsStepsId()
    {
        return ingredientsStepsId;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public Step getStep()
    {
        return step;
    }

    public void setIngredientsStepsId(IngredientsStepsId ingredientsStepsId)
    {
        this.ingredientsStepsId = ingredientsStepsId;
    }

    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
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
        IngredientsSteps that = (IngredientsSteps) o;
        return Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(step, that.step);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ingredient, step);
    }

    @Override
    public String toString()
    {
        return "IngredientsSteps{" +
                "ingredientsStepsId=" + ingredientsStepsId +
                ", ingredient=" + ingredient +
                ", step=" + step +
                '}';
    }
}