package com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse;

import com.tsilva.springFood.entity.Ingredient;
import com.tsilva.springFood.entity.IngredientMetaInformations;
import com.tsilva.springFood.entity.IngredientMetas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Telmo Silva on 28.05.2020.
 */

public class IngredientResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230028L;

    private Long id = null;
    private Long spoonacularId = null;
    private String aisle = null;
    private String image = null;
    private String consistency = null;
    private String name = null;
    private String original = null;
    private String originalString = null;
    private String originalName = null;
    private Double amount = null;
    private String unit = null;
    private List<String> ingredientMetaList = null;
    private List<String> ingredientMetaInformationList = null;
    private MeasureResponse measure = null;

    public IngredientResponse(Ingredient ingredient)
    {
        if(ingredient != null && ingredient.getName() != null)
        {
            this.id = ingredient.getId();
            this.spoonacularId = ingredient.getSpoonacularId();
            this.aisle = ingredient.getAisle();
            this.image = ingredient.getImage();
            this.consistency = ingredient.getConsistency();
            this.name = ingredient.getName();
            this.original = ingredient.getOriginal();
            this.originalString = ingredient.getOriginalString();
            this.originalName = ingredient.getOriginalName();
            this.amount = ingredient.getAmount();
            this.unit = ingredient.getUnit();

            if(ingredient.getIngredientMetas() != null && !ingredient.getIngredientMetas().isEmpty())
            {
                this.ingredientMetaList = new ArrayList<>(ingredient.getIngredientMetas().size());
                for(IngredientMetas ingredientMetas: ingredient.getIngredientMetas())
                {
                    this.ingredientMetaList.add(ingredientMetas.getMeta());
                }
            }

            if(ingredient.getIngredientMetaInformations() != null
                    && !ingredient.getIngredientMetaInformations().isEmpty())
            {
                this.ingredientMetaInformationList = new ArrayList<>(ingredient.getIngredientMetaInformations().size());
                for(IngredientMetaInformations ingredientMetaInformations: ingredient.getIngredientMetaInformations())
                {
                    this.ingredientMetaInformationList.add(ingredientMetaInformations.getMetaInformation());
                }
            }

            if(ingredient.getMeasure() != null)
            {
                this.measure = new MeasureResponse(ingredient.getMeasure());
            }
        }
    }

    public Long getId()
    {
        return id;
    }

    public Long getSpoonacularId()
    {
        return spoonacularId;
    }

    public String getAisle()
    {
        return aisle;
    }

    public String getImage()
    {
        return image;
    }

    public String getConsistency()
    {
        return consistency;
    }

    public String getName()
    {
        return name;
    }

    public String getOriginal()
    {
        return original;
    }

    public String getOriginalString()
    {
        return originalString;
    }

    public String getOriginalName()
    {
        return originalName;
    }

    public Double getAmount()
    {
        return amount;
    }

    public String getUnit()
    {
        return unit;
    }

    public List<String> getIngredientMetaList()
    {
        return ingredientMetaList;
    }

    public List<String> getIngredientMetaInformationList()
    {
        return ingredientMetaInformationList;
    }

    public MeasureResponse getMeasure()
    {
        return measure;
    }
}
